package com.whatfucksoftware.pokedex.service.impl;

import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.dto.PokemonListDTO;
import com.whatfucksoftware.pokedex.service.PokemonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Override
    public List<PokemonListDTO> findAll() {
        return null;
    }

    @Override
    public PokemonDTO findById(String id) {
        return null;
    }

    @Override
    public PokemonDTO create(PokemonDTO pokemon) {
        return null;
    }

    @Override
    public PokemonDTO update(String id, PokemonDTO pokemon) {
        return null;
    }

    @Override
    public PokemonDTO delete(String id) {
        return null;
    }

}
