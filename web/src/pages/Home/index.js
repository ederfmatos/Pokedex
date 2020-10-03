import React, { useState, useEffect } from 'react';
import { PokemonList, Spinner } from '../../components';
import PokemonService from '../../services/PokemonService';

const Home = () => {
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
      <div className="align-center">
        <Spinner />
      </div>
    );
  }

  return (
    <>
      <PokemonList pokemons={pokemons} />
    </>
  );
};

export default Home;
