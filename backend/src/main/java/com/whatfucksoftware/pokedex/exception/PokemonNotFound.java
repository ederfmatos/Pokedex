package com.whatfucksoftware.pokedex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFound extends RuntimeException {

    public PokemonNotFound() {
        super("Pokemon não encontrado!");
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> acbee0f06198474a1869f6d53d0a8344b3998bf7
