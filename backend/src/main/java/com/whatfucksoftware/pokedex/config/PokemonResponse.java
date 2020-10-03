package com.whatfucksoftware.pokedex.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResponse {
    private List<Map<String, String>> results = new ArrayList<>();
}
