package com.whatfucksoftware.pokedex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFound extends RuntimeException {

    public PokemonNotFound() {
        super("Pokemon não encontrado!");
    }

}