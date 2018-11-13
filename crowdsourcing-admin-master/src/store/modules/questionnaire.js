import questionnaire from '../../api/questionnaire';
import types from '../types';
const state = {
  success: '',
  updateing: false,
  error: '',
  tasks: '',
  loading: false,
  loadError: ''
}


const mutations = {
  [types.UPDATEING_QUESTIONNAIRE](state) {
    state.updateing = true;
  },
  [types.ADD_QUESTIONNAIRE_SUCCESS](state, payload) {
    state.updateing = false;
    state.success = payload.msg;
  },
  [types.ADD_QUESTIONNAIRE_ERROR](state, payload) {
    state.updateing = false;
    state.error = payload.error;
  }
 /* [types.LOADING_QUESTIONNAIRES](state){
    state.loading = true;
  },
  [types.LOAD_QUESTIONNAIRE_SUCCESS](state, payload) {
    state.loading = false;
    let tempData = payload.data;
    tempData.forEach((item) => {
      if(new Date(item.end_time).getTime() <= Date.now()) {
        item.task_progress = '100%';
      }
    })
    state.tasks = tempData;
  },
  [types.LOAD_QUESTIONNAIRE_FAILED](state, payload) {
    state.loading = false;
    state.loadError = payload.error
  }*/

}

const actions =  {
  // 返回的数据格式为：
  // { result: { status: 0, error: '', message: 'Success', length: 1 } }
  // { result: { status: 1, error: 'error desc', message: '' } }
  addQuestionnaire({ commit, state }, payload) {
    commit(types.UPDATEING_QUESTIONNAIRE);
    questionnaire.addQuestionnaire(payload.data).then((json) => {

        commit(types.ADD_QUESTIONNAIRE_SUCCESS, { msg: json.result.message });

    }).catch((err) => {
      // 出现请求错误等
      commit(types.ADD_QUESTIONNAIRE_ERROR, { error: '发生'+err.response.status+'错误。' });
    })
  } 
}

export default { 
  state,
  mutations,
  actions
 }