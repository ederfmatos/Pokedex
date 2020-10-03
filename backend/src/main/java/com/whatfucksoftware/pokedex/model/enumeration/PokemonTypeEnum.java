package com.whatfucksoftware.pokedex.model.enumeration;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
public enum PokemonTypeEnum {

    NORMAL(1),
    FIRE(2),
    FIGHTING(3),
    WATER(4),
    FLYING(5),
    GRASS(6),
    POISON(7),
    ELECTRIC(8),
    GROUND(9),
    PYSCHIC(10),
    ROCK(11),
    ICE(12),
    BUG(13),
    DRAGON(14),
    GHOST(15),
    DARK(16),
    STELL(17),
    FAIRY(18),
    ;

    private final int number;

    PokemonTypeEnum(int number) {
        this.number = number;
    }

    public static PokemonTypeEnum of(List<Map<String, Map<String, String>>> types, int number) {
        try {
            return PokemonTypeEnum.valueOf(types.get(number).get("type").get("name").toUpperCase());
        } catch (Throwable throwable) {
            return null;
        }
    }

    public static PokemonTypeEnum of(int number) {
        return Arrays.stream(PokemonTypeEnum.values())
                .filter(pokemonTypeEnum -> pokemonTypeEnum.getNumber() == number)
                .findFirst()
                .orElse(null);
    }

}
