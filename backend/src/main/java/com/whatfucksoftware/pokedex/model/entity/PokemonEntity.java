package com.whatfucksoftware.pokedex.model.entity;

import com.whatfucksoftware.pokedex.mapper.converter.PokemonTypeEnumConverter;
import com.whatfucksoftware.pokedex.model.enumeration.PokemonTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pokemons")
@AllArgsConstructor
@NoArgsConstructor
@Data
@With
@Builder
@Table(name = "pokemons")
public class PokemonEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "pokemon_number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "primary_type", nullable = false)
    @Convert(converter = PokemonTypeEnumConverter.class)
    private PokemonTypeEnum primaryType;

    @Column(name = "secondary_type")
    @Convert(converter = PokemonTypeEnumConverter.class)
    private PokemonTypeEnum secondaryType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "pokemon_images", joinColumns = @JoinColumn(name = "pokemon_id"), foreignKey = @ForeignKey(name = "images_pokemon_fk"))
    @Column(name = "image_url")
    private Set<String> images = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "pokemon_skills", joinColumns = @JoinColumn(name = "pokemon_id"), foreignKey = @ForeignKey(name = "skills_pokemon_fk"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();

}
