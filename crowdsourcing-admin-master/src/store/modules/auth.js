import api from '../../api/auth';
import types from '../types';
const state = {
  userName: '',
  token: null,
  loading: false,
  show: false,
  error: null
}

const getters = {
  isLogin: (state) => state.token !== null
}

const actions = {
  auth({ commit, state }, payload) {
    commit(types.WAIT_FOR_RESPONSE); // 让其变成loading状态
    //这里需要在写好后端逻辑后打开
    // api.auth({ userName: payload.userName, password: payload.password }).then((json) => {
    //   const { userName, token } = json.result;
    //   // commit 成功的mutations
    //   // 保存凭证在本地
    //   localStorage.setItem('userName', payload.userName);
    //   localStorage.setItem('token', token); // 保存在本地的token
    //   commit(types.AUTH_SUCCESS_AND_SAVE_TOKEN, { userName,  token});
    //   commit(types.AFTER_RESPONSE);
    //   payload.$router.push('/dashbord'); 
    //   // 跳转到主页
    // }).catch((err) => {
    //   commit(types.AFTER_RESPONSE);
    //   commit(types.AUTH_FAILED, {error: err.response.data.result.error })
    // })
    setTimeout(function() {
      commit(types.AFTER_RESPONSE);
      // commit(types.AUTH_FAILED, { error:'error happend' });
      localStorage.setItem('userName', 'Limoer');
      localStorage.setItem('token', '2311105'); // 保存在本地的token
      commit(types.AUTH_SUCCESS_AND_SAVE_TOKEN, { userName: 'limoer',  token: '2311105'});
      payload.$router.push('/dashbord'); 
    }, 2000)
    // 模拟后端请求
    // 模拟登录错误
  },
  regist({ state, commit }, payload) {
    commit(types.WAIT_FOR_RESPONSE);
    api.regist({ userName: payload.userName, password: payload.password }).then((json) => {
      const { userName, token } = json.result;
      localStorage.setItem('userName', userName);
      localStorage.setItem('token', token); // 保存在本地的token
      commit(types.AFTER_RESPONSE);
      commit(types.AUTH_SUCCESS_AND_SAVE_TOKEN, { userName,  token});
      payload.$router.push('/dashbord'); 
    }).catch(err => {
      commit(types.AFTER_RESPONSE);
      commit(types.AUTH_FAILED, {error: err.response.data.result.error }) 
    })
  }

}
const mutations = {
  [types.WAIT_FOR_RESPONSE](state) {
    state.loading = true;
  },
  [types.AUTH_SUCCESS_AND_SAVE_TOKEN](state, { userName, token }) {
    state.userName = userName;
    state.token = token;
  },
  [types.AUTH_FAILED](state, { error }) {
    state.error = error
  },
  [types.AFTER_RESPONSE](state) {
    state.loading = false;
  },
  [types.CLEAR_TOKEN_AND_LOGOUT](state, payload) {
    state.userName = '';
    state.token = null;
    state.show = false;
    if(localStorage) {
      localStorage.removeItem('token');
      localStorage.removeItem('userName');
    }
    payload.$router.push('/');
  },
  [types.SHOW_LOGIN_FORM](state) {
    state.show = true;
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}