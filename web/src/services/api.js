import axios from "axios";

const BASE_URL = "http://ec2-3-87-167-199.compute-1.amazonaws.com:8080";

const api = axios.create({
  baseURL: BASE_URL,
});

export default api;
