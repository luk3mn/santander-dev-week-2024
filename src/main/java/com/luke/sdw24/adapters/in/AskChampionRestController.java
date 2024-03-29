package com.luke.sdw24.adapters.in;

import org.springframework.web.bind.annotation.*;

import com.luke.sdw24.application.AskChampionUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Champions", description = "LOL champions endpoint domain")
@RestController
@RequestMapping("/champions")
public record AskChampionRestController(AskChampionUseCase useCase) {

    @CrossOrigin // unsecure practice
    @PostMapping("/{championId}/ask")
    public AskChampionResponse askChampion(@PathVariable Long championId, @RequestBody AskChampionRequest request) {
        String answer = useCase.askChampion(championId, request.question());
        return new AskChampionResponse(answer);
    }

    public record AskChampionRequest(String question) { }
    public record AskChampionResponse(String answer) { }
}
