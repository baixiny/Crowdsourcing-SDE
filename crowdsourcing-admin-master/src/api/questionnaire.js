import axios from 'axios';
const BASE_URL = 'http://211.87.239.31:8080/Spring4MVCCRUDRestService';
export default {
  addQuestionnaire: (data) => axios.post(`${BASE_URL}/question/addone`, {data}).then(res => { return{result: res.data  }}),
}
