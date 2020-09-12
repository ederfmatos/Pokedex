package com.whatfucksoftware.pokedex.model.dto;

import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class PokemonListDTO {

    private String id;
    private String name;
    private List<String> images;
    private PokemonTypeEnum primaryType;
    private PokemonTypeEnum secondaryType;

}
