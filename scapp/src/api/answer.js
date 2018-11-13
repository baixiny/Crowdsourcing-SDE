import axios from 'axios';

export default {
  loadAnswers: () => axios.get('/api/answer/all').then(res => res.data)
}
