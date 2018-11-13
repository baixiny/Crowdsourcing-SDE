import task from '../../api/task';
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
  [types.UPDATEING_TASK](state) {
    state.updateing = true;
  },
  [types.ADD_TASK_SUCCESS](state, payload) {
    state.updateing = false;
    state.success = payload.msg;
  },
  [types.ADD_TASK_ERROR](state, payload) {
    state.updateing = false;
    state.error = payload.error;
  },
  [types.LOADING_TASKS](state){
    state.loading = true;
  },
  [types.LOAD_TASK_SUCCESS](state, payload) {
    state.loading = false;
    state.tasks = payload.data
  },
  [types.LOAD_TASK_FAILED](state, payload) {
    state.loading = false;
    state.loadError = payload.error
  }

}

const actions =  {
  // 返回的数据格式为：
  // { result: { status: 0, error: '', message: 'Success', length: 1 } }
  // { result: { status: 1, error: 'error desc', message: '' } }
  addTask({ commit, state }, payload) {
    commit(types.UPDATEING_TASK);
    task.addTask(payload.data).then(res => res.data).then((json) => {
      if(json.result.status === 0) {
        commit(types.ADD_TASK_SUCCESS, { msg: json.result.message });
      }else {
        commit(types.ADD_TASK_ERROR, { error: json.result.error });
      }
    }).catch((err) => {
      // 出现请求错误等
      commit(types.ADD_TASK_ERROR, { error: '发生'+err.response.status+'错误。' });
    })
  },
  loadTasks({ commit, tasks }, payload) {
    commit(types.LOADING_TASKS);
    task.getAllTasks().then((json) => {
      if(json.result.status === 0 ){
        commit(types.LOAD_TASK_SUCCESS, { data: json.result.data })
      }else {
        commit(types.LOAD_TASK_FAILED, { error: json.result.error })
      }
    }).catch((err) => {
      commit(types.LOAD_TASK_FAILED, { error: `发生了${ err.response.status }错误。` })
    })
  } 
}

export default { 
  state,
  mutations,
  actions
 }




