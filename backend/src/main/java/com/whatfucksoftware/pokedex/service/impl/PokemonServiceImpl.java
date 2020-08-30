package com.whatfucksoftware.pokedex.service.impl;

import com.whatfucksoftware.pokedex.mapper.PokemonMapper;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.dto.PokemonListDTO;
import com.whatfucksoftware.pokedex.model.entity.PokemonEntity;
import com.whatfucksoftware.pokedex.repository.PokemonRepository;
import com.whatfucksoftware.pokedex.service.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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
        log.info("Realizando criação de pokemon [{}]", pokemon);
        pokemon.setId(UUID.randomUUID().toString());
        PokemonEntity pokemonEntity = pokemonMapper.toEntity(pokemon);
        pokemonRepository.save(pokemonEntity);
        return pokemon;
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
