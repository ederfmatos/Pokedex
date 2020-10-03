package com.whatfucksoftware.pokedex.config;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PokemonA {

    private String name;
    private Double weight;
    private Double number;
    private List<Map<String, Map<String, String>>> types = new ArrayList<>();

}
