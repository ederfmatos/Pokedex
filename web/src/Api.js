const BASE_URL = 'http://192.34.58.124:8080/';

const Api = {
  get: async (path) => {
    return fetch(`${BASE_URL}${path}`, {
      method: 'GET',
    }).then((response) => response.json());
  },
};

export default Api;
