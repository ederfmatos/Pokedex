package com.whatfucksoftware.pokedex.controller;

import com.whatfucksoftware.pokedex.exception.InvalidArgumentsException;
import com.whatfucksoftware.pokedex.exception.PokemonNotFound;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PokemonNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors pokemonNotFound(PokemonNotFound exception) {
        return new ApiErrors(HttpStatus.NOT_FOUND.value(), exception);
    }

    @ExceptionHandler(InvalidArgumentsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors invalidArgumentsException(InvalidArgumentsException exception) {
        return new ApiErrors(HttpStatus.BAD_REQUEST.value(), exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors validationErrors(MethodArgumentNotValidException exception) {
        return new ApiErrors(HttpStatus.BAD_REQUEST.value(), exception.getBindingResult());
    }

    @Getter
    public static class ApiErrors {
        private final Long timestamp;
        private final Integer statusCode;
        private final List<String> errors;

        public ApiErrors(Integer statusCode, Exception e) {
            this.timestamp = System.currentTimeMillis();
            this.statusCode = statusCode;
            this.errors = Collections.singletonList(e.getMessage());
        }

        public ApiErrors(Integer statusCode, BindingResult bindingResult) {
            this.timestamp = System.currentTimeMillis();
            this.statusCode = statusCode;
            this.errors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
        }
    }
}


