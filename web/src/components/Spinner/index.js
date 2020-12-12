import React from 'react';

const Spinner = ({ className, ...rest }) => {
  return <span {...rest} className={className ? className : 'spinner'}></span>;
};

export default Spinner;
