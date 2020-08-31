package com.whatfucksoftware.pokedex.controller;

import com.whatfucksoftware.pokedex.PokemonRoutes;
import com.whatfucksoftware.pokedex.model.dto.PokemonDTO;
import com.whatfucksoftware.pokedex.model.dto.PokemonListDTO;
import com.whatfucksoftware.pokedex.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PokemonRoutes.POKEMONS)
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonListDTO> findAll() {
        return pokemonService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PokemonDTO findById(@PathVariable("id") String id) {
        return pokemonService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PokemonDTO create(@RequestBody PokemonDTO pokemon) {
        return pokemonService.create(pokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PokemonDTO update(@PathVariable("id") String id, @RequestBody PokemonDTO pokemon) {
        return pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        pokemonService.delete(id);
    }

}
