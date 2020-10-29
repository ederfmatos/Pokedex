import React from 'react';

const Icon = ({ children, clickable, size }) => {
  console.log(clickable);
  return (
    <div
      className={`icon ${clickable ? 'icon--clickable' : ''} ${
        size ? 'icon--' + size : ''
      }`}
    >
      {children}
    </div>
  );
};

export default Icon;
