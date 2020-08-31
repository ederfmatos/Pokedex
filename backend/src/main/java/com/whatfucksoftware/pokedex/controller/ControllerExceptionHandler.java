package com.whatfucksoftware.pokedex.controller;
import com.whatfucksoftware.pokedex.exception.PokemonNotFound;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PokemonNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors pokemonNotFound(PokemonNotFound exception){
        return new ApiErrors(HttpStatus.NOT_FOUND.value(), exception);
    }

}

@Getter
class ApiErrors {
    private Long timestamp;
    private Integer statusCode;
    private List<String> errors;

    ApiErrors(Integer statusCode, PokemonNotFound e){
        timestamp = System.currentTimeMillis();
        this.statusCode = statusCode;
        errors = Arrays.asList(e.getMessage());
    }
}

