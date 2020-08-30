package com.whatfucksoftware.pokedex.service;

import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.dto.PokemonListDTO;

import java.util.List;

public interface PokemonService {

    List<PokemonListDTO> findAll();

    PokemonDTO findById(String id);

    PokemonDTO create(PokemonDTO pokemon);

    PokemonDTO update(String id, PokemonDTO pokemon);

    PokemonDTO delete(String id);

}
