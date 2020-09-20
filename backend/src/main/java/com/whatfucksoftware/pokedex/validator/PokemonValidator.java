package com.whatfucksoftware.pokedex.validator;

import com.whatfucksoftware.pokedex.exception.InvalidArgumentsException;
import com.whatfucksoftware.pokedex.exception.PokemonNotFound;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.entity.PokemonEntity;
import com.whatfucksoftware.pokedex.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PokemonValidator {

    private final PokemonRepository pokemonRepository;

    public void validateCreate(PokemonDTO pokemon) {
        if (pokemonRepository.existsByName(pokemon.getName())) {
            throw new InvalidArgumentsException("Já existe um pokemon com esse nome, escolha outro");
        }

        if (pokemonRepository.existsByNumber(pokemon.getNumber())) {
            throw new InvalidArgumentsException("Já existe um pokemon com esse número, escolha outro");
        }
    }

    public void validateUpdate(String id, PokemonDTO pokemonDto) {
        if (!pokemonRepository.existsById(id)) {
            throw new PokemonNotFound();
        }

        Optional<PokemonEntity> pokemonWithNumber = pokemonRepository.findByNumber(pokemonDto.getNumber());
        if (pokemonWithNumber.isPresent() && !pokemonWithNumber.get().getId().equals(id)) {
            throw new InvalidArgumentsException("Já existe um pokemon com esse número, escolha outro");
        }

        Optional<PokemonEntity> pokemonWithName = pokemonRepository.findByName(pokemonDto.getName());
        if (pokemonWithName.isPresent() && !pokemonWithName.get().getId().equals(id)) {
            throw new InvalidArgumentsException("Já existe um pokemon com esse nome, escolha outro");
        }
    }

    public void validateDelete(String id) {
        if (!pokemonRepository.existsById(id)) {
            throw new PokemonNotFound();
        }
    }

}
