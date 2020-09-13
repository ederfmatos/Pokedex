import React from 'react';
import './Header.scss';

const Header = () => {
  const header = React.useRef();
  const [fixed, setFixed] = React.useState(false);
  React.useEffect(() => {
    function scrollEvent() {
      setFixed(window.scrollY > header.current.offsetHeight * 0.25);
    }

    window.addEventListener('scroll', scrollEvent);
    return () => {
      window.removeEventListener('scroll', scrollEvent);
    };
  }, []);

  return (
    <header ref={header} className={`header ${fixed && 'header--fixed'}`}>
      <h2>Pokédex</h2>
    </header>
  );
};

export default Header;
