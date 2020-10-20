import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import ImageLoader from '../../components/ImageLoader';
import { Error, Spinner } from '../../components';
import { MdKeyboardBackspace } from 'react-icons/md';
import PokemonType from '../../components/Pokemon/PokemonType';
import DefensiveMatchups from '../../Utils/DefensiveMatchups';

const Pokemon = props => {
  const { number } = useParams();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [pokemon, setPokemon] = useState({});

  const matchups = DefensiveMatchups('grass', 'poison');
  // useEffect(() => {
  //   PokemonService.findByNumber(number)
  //     .then(response => {
  //       setError(null);
  //       setPokemon(response);
  //     })
  //     .catch(setError)
  //     .finally(() => setLoading(false));
  // }, []);

  if (error) return <Error error={error} className="align-center fullScreen" />;

  return (
    <div className={`pokemonPage ${loading ? '' : 'type-grass--dark'}`}>
      <div className={`pokemonHeader ${loading ? '' : 'pokemonHeader--white'}`}>
        <MdKeyboardBackspace className="back" />
        <h1 className="title title--big">Bulbasaur</h1>
        <span className="text bold">#001</span>
      </div>
      {loading ? (
        <div className="align-center fullScreen">
          <Spinner />{' '}
        </div>
      ) : (
        <>
          <div className="imageWrapper">
            <ImageLoader
              src="https://pokeres.bastionbot.org/images/pokemon/1.png"
              alt="Bulbasaur"
            />
          </div>
          <div className="infoWrapper">
            <div className="types">
              <PokemonType type="grass" />
              <PokemonType type="poison" />
            </div>
            <h1 className="title title--secondary">Fraquezas</h1>
            <div className="matchups">
              {matchups
                .filter(match => match.effective > 1)
                .map(match => (
                  <PokemonType type={match.type}></PokemonType>
                ))}
            </div>
            <h1 className="title title--secondary">Resistencias</h1>
            <div className="matchups">
              {matchups
                .filter(match => match.effective < 1)
                .map(match => (
                  <PokemonType type={match.type}></PokemonType>
                ))}
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default Pokemon;
