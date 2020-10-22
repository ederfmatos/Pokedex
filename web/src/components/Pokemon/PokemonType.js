import React from 'react';

const PokemonType = ({ type, effective }) => {
  return (
    <div className={`type type-${type.toLowerCase()}`}>
      <img
        src={require(`../../Assets/types/${type.toLowerCase()}.svg`)}
        alt={type}
      />
      <span className="text">
        {type.charAt(0) + type.slice(1).toLowerCase()}
      </span>
      {effective && <span className="text">x{effective}</span>}
    </div>
  );
};

export default PokemonType;
