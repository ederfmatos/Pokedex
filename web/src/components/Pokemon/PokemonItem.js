import React from 'react';

const PokemonItem = () => {
  return (
    <div className="pokemon type-fire">
      <idiv className="pokemon__imageContainer">
        <img
          className="pokemon__image"
          src="https://vignette.wikia.nocookie.net/pocketmonster/images/f/ff/Mega_Charizard_Y.png/revision/latest?cb=20160909131912&path-prefix=pt-br"
        />
      </idiv>
      <div className="pokemon__content">
        <label className="pokemon__number">Nº 4</label>
        <label className="pokemon__name">Charmander</label>
      </div>
    </div>
  );
};

export default PokemonItem;
