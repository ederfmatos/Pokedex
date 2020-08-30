package com.whatfucksoftware.pokedex.service.impl;

import com.whatfucksoftware.pokedex.mapper.PokemonMapper;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.dto.PokemonListDTO;
import com.whatfucksoftware.pokedex.repository.PokemonRepository;
import com.whatfucksoftware.pokedex.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonMapper pokemonMapper;
    private final PokemonRepository pokemonRepository;

    @Override
    public List<PokemonListDTO> findAll() {
        return pokemonRepository.findAll()
                .stream()
                .map(pokemonMapper::toListDTO)
                .collect(Collectors.toList());
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
