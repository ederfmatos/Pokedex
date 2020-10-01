import React from 'react';
import Api from './Api';
import './App.scss';
import Header from './components/Header';
import PokemonList from './components/Pokemon/PokemonList';

function App() {
  React.useEffect(() => {
    Api.get('/pokemons')
      .then((data) => console.log(data))
      .catch((err) => console.log(err));
  }, []);
  return (
    <div style={{ height: '150vh' }}>
      <Header />
      <main>
        <PokemonList />
      </main>
    </div>
  );
}

export default App;
