import axios from 'axios';

export default {
  addTask: (data) => axios.post('/api/task/addone', {data}),
  getAllTasks: () => axios.post('/api/task/all').then(res => res.data)
}

