import React, { useState, useEffect } from 'react';
import PokemonItem from './PokemonItem';
import PokemonService from '../../services/PokemonService';
import Spinner from '../Spinner';

const PokemonList = () => {
  const [loading, setLoading] = useState(true);
  const [pokemons, setPokemons] = useState([]);

  useEffect(() => {
    setLoading(true);
    PokemonService.findAll()
      .then(setPokemons)
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return (
      <div className="app-align-center">
        <Spinner />
      </div>
    );
  }

  return (
    <div className="pokemonList">
      {pokemons.map(pokemon => {
        return (
          <>
            <PokemonItem pokemon={pokemon} />
            <PokemonItem pokemon={pokemon} />
            <PokemonItem pokemon={pokemon} />
            <PokemonItem pokemon={pokemon} />
            <PokemonItem pokemon={pokemon} />
          </>
        );
      })}
    </div>
  );
};

export default PokemonList;
