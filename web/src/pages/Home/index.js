import React, { useState, useEffect } from "react";
import { PokemonList, Spinner } from "../../components";
import PokemonService from "../../services/PokemonService";

const Home = () => {
  const [loading, setLoading] = useState(false);
  const [pokemons, setPokemons] = useState([]);

  useEffect(() => {
    setLoading(true);

    PokemonService.findAll()
      .then(setPokemons)
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return (
      <div>
        <Spinner />
      </div>
    );
  }

  return (
    <div>
      <PokemonList pokemons={pokemons} />
    </div>
  );
};

export default Home;
