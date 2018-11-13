import types from '../types';
import user from '../../api/user';
const state = {
  data: '',
  loading: '',
  error: ''
}

const mutations = {
  [types.LOADING_USER_DATA](state) {
    state.loading = true;
  },
  [types.LOAD_USER_DATA_SUCCESS](state, payload) {
    state.data = payload.data;
    state.loading = false;
  },
  [types.LOAD_USER_DATA_FAILED](state, payload) {
    state.error = payload.error;
    this.loading = false;
  }
}


const actions = {
  loadUserInfo({ commit, state }) {
    commit(types.LOADING_USER_DATA);
    user.loadUserData().then((json) => {
      if(json.result.status === 0) {
        commit(types.LOAD_USER_DATA_SUCCESS, { data: json.result.data });
      }else {
        commit(types.LOAD_USER_DATA_FAILED, { error: json.result.error });
      }
    }).catch(err => {
      commit(types.LOAD_USER_DATA_FAILED, { error: `发生了${err.response.status}错误` });
    })
  }
}

export default {
  state,
  mutations,
  actions
}

