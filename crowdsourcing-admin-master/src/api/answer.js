import axios from 'axios';
const BASE_URL = 'http://211.87.239.31:8080/Spring4MVCCRUDRestService';
export default {
  loadAnswers: () => axios.get(`${BASE_URL}/answer/all`).then(res => { return {result: res.data} })
}
