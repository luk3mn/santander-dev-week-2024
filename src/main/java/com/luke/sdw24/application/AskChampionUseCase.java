package com.luke.sdw24.application;

import com.luke.sdw24.domain.exception.ChampionNotFoundException;
import com.luke.sdw24.domain.model.Champion;
import com.luke.sdw24.domain.ports.ChampionsRepository;

public record AskChampionUseCase(ChampionsRepository repository) {

    public String askChampion(Long championId, String question) {

        Champion champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String championContext = champion.generateContextByQuestion(question);

        // TODO: improve to include generative ai at this point

        return championContext;
    }
}
