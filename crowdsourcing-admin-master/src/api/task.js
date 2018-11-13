import axios from 'axios';
const BASE_URL = 'http://211.87.239.31:8080/Spring4MVCCRUDRestService';
export default {
  addTask: (data) => axios.post(`${BASE_URL}/task/addone`, {data}).then(res => { return{result: res.data  }}),

  
  getAllTasks: (data) => axios.get(`${BASE_URL}/task/all`).then(res => { return{result: res.data }})
  //getAllTasks: (data) => axios.get(`${BASE_URL}/task/pagegation?page=${data.page}&page_size=${data.pageSize}`).then(res => { return{result: res.data }})
}

