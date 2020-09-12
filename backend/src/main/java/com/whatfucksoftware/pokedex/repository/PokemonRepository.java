package com.whatfucksoftware.pokedex.repository;

import com.whatfucksoftware.pokedex.model.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, String> {

    boolean existsByNumber(int number);

    boolean existsByName(String name);

    Optional<PokemonEntity> findByNumber(int number);

    Optional<PokemonEntity> findByName(String name);

}
