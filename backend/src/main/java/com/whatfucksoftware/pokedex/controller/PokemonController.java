package com.whatfucksoftware.pokedex.controller;

import com.whatfucksoftware.pokedex.PokemonRoutes;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.dto.PokemonListDTO;
import com.whatfucksoftware.pokedex.service.PokemonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(PokemonRoutes.POKEMONS)
@RequiredArgsConstructor
@Api(tags = "Pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Return a list of pokemons"))
    @ApiOperation("List of pokemons")
    public List<PokemonListDTO> findAll() {
        return pokemonService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search details of a pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a pokemon"),
            @ApiResponse(code = 404, message = "Pokemon not found", response = ControllerExceptionHandler.ApiErrors.class),
    })
    public PokemonDTO findById(@PathVariable("id") String id) {
        return pokemonService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a pokemon")
    @ApiResponses(value = @ApiResponse(code = 204, message = "Pokemon successfully created"))
    public PokemonDTO create(@RequestBody @Valid PokemonDTO pokemon) {
        return pokemonService.create(pokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update a pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pokemon successfully updated"),
            @ApiResponse(code = 404, message = "Pokemon not found", response = ControllerExceptionHandler.ApiErrors.class),
    })
    public PokemonDTO update(@PathVariable("id") String id, @RequestBody PokemonDTO pokemon) {
        return pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pokemon successfully deleted"),
            @ApiResponse(code = 404, message = "Pokemon not found", response = ControllerExceptionHandler.ApiErrors.class),
    })
    public void delete(@PathVariable("id") String id) {
        pokemonService.delete(id);
    }

}
