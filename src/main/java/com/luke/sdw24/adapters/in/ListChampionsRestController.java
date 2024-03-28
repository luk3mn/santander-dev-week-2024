package com.luke.sdw24.adapters.in;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luke.sdw24.application.ListChampionsUseCase;
import com.luke.sdw24.domain.model.Champion;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Champions", description = "LOL champions endpoint domain")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {

    @GetMapping
    public List<Champion> findAllChampions() {
        return useCase.findAll();
    }
}
