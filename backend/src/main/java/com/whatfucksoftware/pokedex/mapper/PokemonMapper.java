package com.whatfucksoftware.pokedex.mapper;

import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.dto.PokemonListDTO;
import com.whatfucksoftware.pokedex.model.entity.PokemonEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class PokemonMapper {

    private final ModelMapper modelMapper;

    public PokemonListDTO toListDTO(PokemonEntity pokemonEntity) {
        return modelMapper.map(pokemonEntity, PokemonListDTO.class);
    }

    public PokemonEntity toEntity(PokemonDTO pokemon) {
        return modelMapper.map(pokemon, PokemonEntity.class);
    }

    public PokemonDTO toDTO(PokemonEntity pokemon) {
        return modelMapper.map(pokemon, PokemonDTO.class);
    }

}
