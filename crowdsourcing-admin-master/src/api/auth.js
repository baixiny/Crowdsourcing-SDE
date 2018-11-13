import axios from 'axios';

import qs from 'qs';


export default {
  auth: (data) => axios.post('http://211.87.239.31:8080/Spring4MVCCRUDRestService/worker/login', qs.stringify(data)).then(res => res.data),
  confirmToken: () => axios.get('/api/auth/confirm_token').then(res => res.data),
  regist:(data) => axios.post('http://211.87.239.31:8080/Spring4MVCCRUDRestService/worker/regist', { ...data}).then(res => res.data)
}
