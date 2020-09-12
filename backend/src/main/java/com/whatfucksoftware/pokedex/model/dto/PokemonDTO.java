package com.whatfucksoftware.pokedex.model.dto;

import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PokemonDTO {

    private String id;
    private String name;
    private Integer number;
    private PokemonTypeEnum primaryType;
    private PokemonTypeEnum secondaryType;
    private Set<String> images = new HashSet<>();
    private Set<String> skills = new HashSet<>();

}
