package com.luke.sdw24;

import com.luke.sdw24.domain.ports.GenerativeAiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.luke.sdw24.application.AskChampionUseCase;
import com.luke.sdw24.application.ListChampionsUseCase;
import com.luke.sdw24.domain.ports.ChampionsRepository;

@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository) {
		return new ListChampionsUseCase(repository);
	}

	@Bean
	public AskChampionUseCase provideAskChampionUseCase(ChampionsRepository repository, GenerativeAiService genAiService) {
		return new AskChampionUseCase(repository, genAiService);
	}

}
