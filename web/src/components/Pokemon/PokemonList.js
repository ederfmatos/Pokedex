import React from 'react';
import PokemonItem from './PokemonItem';

const PokemonList = ({ pokemons }) => {
  return (
    <div className="pokemonList">
      {pokemons.map(pokemon => {
        return <PokemonItem key={pokemon.id} pokemon={pokemon} />;
      })}
    </div>
  );
};

export default PokemonList;
