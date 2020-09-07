package com.whatfucksoftware.pokedex.model.dto;

import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;
import lombok.*;

import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PokemonDTO {

    private String id;

    @NotNull
    @Size(min = 1, max = 100, message = "Digite um nome entre 1 e 100 caracteres")
    private String name;

    @NotNull
    @Positive( message =  "O numero deve ser positivo")
    private Integer number;

    @NotNull
    private PokemonTypeEnum primaryType;

    @NotNull
    private PokemonTypeEnum secondaryType;

    @NotEmpty
    private Set<String> images = new HashSet<>();

    private Set<String> skills = new HashSet<>();

}
