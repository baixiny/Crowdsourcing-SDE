import types from '../types';
import answer from '../../api/answer';
const state = {
  data: '',
  loading: false,
  error: ''
}


const mutations = {
  [types.LOADING_ANSWERS](state) {
    state.loading = true;
  },
  [types.LOAD_ANSWERS_SUCCESS](state, payload) {
    state.data = payload.data;
    state.loading = false;
  },
  [types.LOAD_ANSWERS_FAILED](state, payload) {
    state.error = payload.error;
    state.loading = false;
  }
}

const actions = {
  loadAnswers({ commit, state }) {
    commit(types.LOADING_ANSWERS);
    answer.loadAnswers().then(json => {
      if(json.result.status === 0) {
        commit(types.LOAD_ANSWERS_SUCCESS, { data: json.result.data });
      }else {
        commit(types.LOAD_ANSWERS_FAILED, { error: json.result.error });
      }
    }).catch(err => {
      commit(types.LOAD_ANSWERS_FAILED, { error: `发生了${err.response.status}错误` });
    })
  }
}


export default {
  state,
  mutations,
  actions
}

