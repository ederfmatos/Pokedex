import React from 'react';
import PokemonType from './PokemonType';

const PokemonItem = ({ pokemon }) => {
  return (
    <div className="pokemon type-fire">
      <idiv className="pokemon__imageContainer">
        <img
          className="pokemon__image"
          src={pokemon.images[0]}
          alt={pokemon.name}
        />
      </idiv>
      <div className="pokemon__content">
        <label className="pokemon__number">Nº {pokemon.number}</label>
        <label className="pokemon__name">{pokemon.name}</label>
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
