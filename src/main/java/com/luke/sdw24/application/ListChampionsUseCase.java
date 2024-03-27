package com.luke.sdw24.application;

import java.util.List;

import com.luke.sdw24.domain.model.Champion;
import com.luke.sdw24.domain.ports.ChampionsRepository;

public record ListChampionsUseCase(ChampionsRepository repository) {

    public List<Champion> findAll() {
        return repository.findAll();
    }
}
