import React from 'react';
import ImageLoader from '../ImageLoader';
import PokemonType from './PokemonType';
import { useHistory } from 'react-router-dom';

const PokemonItem = ({ pokemon }) => {
  const history = useHistory();

  return (
    <div
      onClick={() => history.push(`/pokemon/${pokemon.id}`)}
      className={`pokemon type-${pokemon.primaryType.toLowerCase()}--dark`}
    >
      <div className="pokemon__imageContainer">
        <ImageLoader
          className="pokemon__image"
          src={pokemon.images[0]}
          alt={pokemon.name}
        />
      </div>
      <div className="pokemon__content">
        <label className="text text--small bold">Nº {pokemon.number}</label>
        <label className="text text--big bold">{pokemon.name}</label>
        <div className="pokemon__types">
          <PokemonType type={pokemon.primaryType} />
          {pokemon.secondaryType && (
            <PokemonType type={pokemon.secondaryType} />
          )}
        </div>
      </div>
    </div>
  );
};

export default PokemonItem;
