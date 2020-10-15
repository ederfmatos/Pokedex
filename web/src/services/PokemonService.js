import api from './api';

class PokemonService {
  findAll() {
    return api.get('/pokemons').then(({ data }) => data);
  }

  findByNumber(number) {
    return api.get(`/pokemon'/${number}`).then(({ data }) => data);
  }
}

export default new PokemonService();
