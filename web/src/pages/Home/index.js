import React from "react";
// import Api from './Api';
import { Spinner } from "../../components";

const Home = () => {
  // React.useEffect(() => {
  //   Api.get('/pokemons')
  //     .then((data) => console.log(data))
  //     .catch((err) => console.log(err));
  // }, []);
  return (
    <div>
      <Spinner />
    </div>
  );
};

export default Home;
