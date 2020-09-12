package com.whatfucksoftware.pokedex.exception;

public class PokemonNotFound extends RuntimeException {
    public PokemonNotFound() {
        super("Pokemon não encontrado!");
    }
}

