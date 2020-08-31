package com.whatfucksoftware.pokedex.service.impl;

import com.whatfucksoftware.pokedex.exception.PokemonNotFound;
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
        return pokemonRepository.findById(id)
                .map(pokemonMapper::toDTO)
                .orElseThrow(PokemonNotFound::new);
    }

    @Override
    public PokemonDTO create(PokemonDTO pokemon) {
        log.info("Realizando criação de pokemon [{}]", pokemon);
        pokemon.setId(UUID.randomUUID().toString());
        pokemonRepository.save(pokemonMapper.toEntity(pokemon));
        return pokemon;
    }

    @Override
    public PokemonDTO update(String id, PokemonDTO pokemonDto) {
        if( !pokemonRepository.existsById(id) ){
            throw new PokemonNotFound();
        }
        PokemonEntity pokemon = pokemonMapper.toEntity(pokemonDto);
        pokemon.setId(id);
        return pokemonMapper.toDTO(pokemonRepository.save(pokemon));
    }

    @Override
    public void delete(String id) {
        if( !pokemonRepository.existsById(id) ){
            throw new PokemonNotFound();
        }
        pokemonRepository.deleteById(id);
    }

}
