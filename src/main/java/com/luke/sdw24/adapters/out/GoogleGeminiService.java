package com.luke.sdw24.adapters.out;

import com.luke.sdw24.domain.ports.GenerativeAiService;
import feign.FeignException;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "GEMINI")
@FeignClient(name = "geminiApi", url = "${gemini.base-url}", configuration = GoogleGeminiService.Config.class)
public interface GoogleGeminiService extends GenerativeAiService {

    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    GoogleGeminiResp textOnlyInput(GoogleGeminiReq req);

    @Override
    default String generateContent(String objective, String context) {
        String prompt = """
                %s
                %s
                """.formatted(objective, context);

        GoogleGeminiReq req = new GoogleGeminiReq(
                List.of(new Content(List.of(new Part(prompt))))
        );
        try {
            GoogleGeminiResp resp = textOnlyInput(req);
            return resp.candidates().getFirst().content().parts().getFirst().text();
        } catch (FeignException httpErrors) {
            return "Ops! Something went wrong to communicate with Google Gemini API.";
        } catch (Exception unexpectedError) {
            return "Ops! Ops! The Google Gemini API return doesn't have any expected data.";
        }
    }

    record GoogleGeminiReq(List<Content> contents) { }
    record Content(List<Part> parts) { }
    record Part(String text) { }
    record GoogleGeminiResp(List<Candidate> candidates) { }
    record Candidate(Content content) { }

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.query("key", apiKey);
        }
    }

}
