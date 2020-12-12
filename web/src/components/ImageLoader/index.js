import React, { useState } from 'react';
import Spinner from '../Spinner';

const ImageLoader = props => {
  const [loaded, setLoaded] = useState(false);

  function imageLoaded(params) {
    setLoaded(true);
  }

  function error() {
    setLoaded(true);
    props.src = require(`../../Assets/question.svg`);
  }

  return (
    <>
      {loaded ? null : (
        <Spinner className="simple-spinner  simple-spinner--medium" />
      )}
      <img
        {...props}
        style={{ display: loaded ? 'block' : 'none' }}
        onLoad={imageLoaded}
        onError={error}
      />
    </>
  );
};

export default ImageLoader;
