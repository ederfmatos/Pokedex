import React from 'react';

const Header = () => {
  const [fixed, setFixed] = React.useState(false);

  React.useEffect(() => {
    function scrollEvent() {
      setFixed(window.scrollY > 10);
    }

    window.addEventListener('scroll', scrollEvent);
    return () => {
      window.removeEventListener('scroll', scrollEvent);
    };
  }, [fixed]);

  return (
    <header className={`header${fixed ? ' header--fixed' : ''}`}>
      <h3 className="title title--primary">Pokédex</h3>
    </header>
  );
};

export default Header;
