import React, { useState, useEffect } from 'react';
import PokemonItem from './PokemonItem';
import PokemonService from '../../services/PokemonService';
import Spinner from '../Spinner';
import Error from '../Error';

const PokemonList = () => {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState({});
  const [pokemons, setPokemons] = useState([]);

  useEffect(() => {
    setLoading(true);
    PokemonService.findAll()
      .then(response => {
        setError(null);
        setPokemons(response);
      })
      .catch(setError)
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return (
      <div className="app-align-center">
        <Spinner />
      </div>
    );
  }

  if (error) return <Error error={error} className="app-align-center" />;

  return (
    <div className="pokemonList">
      {pokemons.map(pokemon => {
        return <PokemonItem key={pokemon.id} pokemon={pokemon} />;
      })}
    </div>
  );
};

export default PokemonList;
