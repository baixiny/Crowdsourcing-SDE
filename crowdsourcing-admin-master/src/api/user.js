import axios from 'axios';
const BASE_URL = 'http://211.87.239.31:8080/Spring4MVCCRUDRestService';
export default {
  loadUserData: () => axios.get( `${BASE_URL}/user/all`).then(res => {return{result:res.data}})
}

