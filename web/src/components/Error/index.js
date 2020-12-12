import React from 'react';
import { ReactComponent as SadPikachu } from '../../Assets/sad_pikachu.svg';
import './Error.scss';

const Error = ({ error, className }) => {
  return (
    <div className={`error ${className}`}>
      <SadPikachu />
      <label className="text text-error">{error.message}</label>
    </div>
  );
};

export default Error;
