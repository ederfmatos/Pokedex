import React from 'react';

const PokemonType = ({ type }) => {
  return (
    <div className={`type type-${type.toLowerCase()}`}>
      <img src={require(`../../Assets/types/${type.toLowerCase()}.svg`)} />
      <span className="text">
        {type.charAt(0) + type.slice(1).toLowerCase()}
      </span>
    </div>
  );
};

export default PokemonType;
