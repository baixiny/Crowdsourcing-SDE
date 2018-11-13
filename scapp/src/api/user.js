import axios from 'axios';

export default {
  loadUserData: () => axios.get('/api/user/all').then(res => res.data)
}

