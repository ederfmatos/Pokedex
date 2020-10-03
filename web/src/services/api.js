const BASE_URL = "http://ec2-3-87-167-199.compute-1.amazonaws.com:8080";

const Api = {
  get: async (path) => {
    return fetch(`${BASE_URL}${path}`, {
      method: "GET",
    }).then((response) => response.json());
  },
};

export default Api;
