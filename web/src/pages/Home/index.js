import React from 'react';
import { PokemonList, Header } from '../../components';

const Home = () => {
  return (
    <>
      <Header />
      <main className="homePage">
        <PokemonList />
      </main>
    </>
  );
};

export default Home;
