package com.luke.sdw24.domain.ports;

import java.util.List;
import java.util.Optional;

import com.luke.sdw24.domain.model.Champion;

public interface ChampionsRepository {

    List<Champion> findAll(); // List all champions

    Optional<Champion> findById(Long id); // Optional: return a champion if the 'id' doesn't exist

}
