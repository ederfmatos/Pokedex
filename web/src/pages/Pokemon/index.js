import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import ImageLoader from '../../components/ImageLoader';
import { Error, Icon, Spinner } from '../../components';
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
        <Link to="/" className="icon icon--clickable icon--big">
          <MdKeyboardBackspace />
        </Link>
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
                  <PokemonType
                    key={match.type}
                    type={match.type}
                    effective={match.effective}
                  ></PokemonType>
                ))}
            </div>
            <h1 className="title title--secondary">Resistencias</h1>
            <div className="matchups">
              {matchups
                .filter(match => match.effective < 1)
                .map(match => (
                  <PokemonType
                    key={match.type}
                    type={match.type}
                    effective={match.effective}
                  ></PokemonType>
                ))}
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default Pokemon;
