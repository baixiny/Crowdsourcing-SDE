import axios from 'axios';

export default {
  auth: (data) => axios.post('http://211.87.239.94:8080/Spring4MVCCRUDRestService/worker/', {data}).then(res => res.data),
  confirmToken: () => axios.get('/api/auth/confirm_token').then(res => res.data)
}

