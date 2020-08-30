package com.whatfucksoftware.pokedex.repository;

import com.whatfucksoftware.pokedex.model.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, String> {

}
