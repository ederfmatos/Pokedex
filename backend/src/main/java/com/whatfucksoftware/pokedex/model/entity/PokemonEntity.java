package com.whatfucksoftware.pokedex.model.entity;

import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pokemons")
@AllArgsConstructor
@NoArgsConstructor
@Data
@With
@Table(name = "pokemons")
public class PokemonEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "primaryType", nullable = false)
    @Enumerated(EnumType.STRING)
    private PokemonTypeEnum primaryType;

    @Column(name = "secondaryType")
    @Enumerated(EnumType.STRING)
    private PokemonTypeEnum secondaryType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "pokemon_images")
    private Set<String> images = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "pokemon_skills")
    private Set<String> skills = new HashSet<>();

}
