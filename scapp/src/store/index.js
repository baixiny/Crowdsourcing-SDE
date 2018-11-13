import Vue from 'vue';
import Vuex from 'vuex';
import auth from './modules/auth';
import task from './modules/task';
import answer from './modules/answer';
import user from './modules/user';
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    task,
    answer,
    user
  }
})

