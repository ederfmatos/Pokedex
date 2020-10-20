import React, { useState, useEffect } from 'react';
import { PokemonList, Header, Spinner, Error } from '../../components';
import PokemonService from '../../services/PokemonService';

const Home = () => {
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

  return (
    <>
      <Header />
      {loading ? (
        <div className="align-center fullScreen">
          <Spinner />
        </div>
      ) : error ? (
        <Error
          error={error}
          className="align-center 
        "
        />
      ) : (
        <main className="homePage">
          <PokemonList pokemons={pokemons} />
        </main>
      )}
    </>
  );
};

export default Home;
