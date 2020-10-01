import React from 'react';
import './Header.scss';

const Header = () => {
  const header = React.useRef();
  const [fixed, setFixed] = React.useState(false);
  React.useEffect(() => {
    function scrollEvent() {
      if (fixed !== window.scrollY > header.current.offsetHeight * 0.25)
        setFixed(!fixed);
    }

    window.addEventListener('scroll', scrollEvent);
    return () => {
      window.removeEventListener('scroll', scrollEvent);
    };
  }, [fixed]);

  return (
    <header ref={header} className={`header${fixed ? ' header--fixed' : ''}`} a>
      <h2 className="header__title">Pokédex</h2>
    </header>
  );
};

export default Header;
