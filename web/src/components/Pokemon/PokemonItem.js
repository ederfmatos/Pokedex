import React from 'react';
import './PokemonItem.scss';

const PokemonItem = () => {
  return (
    <div className="pokemon type-fire">
      <div className="pokemon__image"></div>
      <div className="pokemon__content">
        <label className="pokemon__number">Nº 4</label>
        <label className="pokemon__name">Charmander</label>
        <div>TESTE</div>
      </div>
    </div>
  );
};

export default PokemonItem;
