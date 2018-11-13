// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'lib-flexible/flexible.js'
import Weui from 'weui'
import axios from 'axios'

import 'iview/dist/styles/iview.css'
import store from './store';
import iView from 'iview';

//import MintUI from 'mint-ui'
//import 'mint-ui/lib/style.css'
/*import VueAxios from 'vue-axios' 
Vue.use(VueAxios, axios)*/

Vue.config.productionTip = false
Vue.use(Weui);
Vue.prototype.$ajax = axios;
Vue.use(iView);

//Vue.use(MintUI)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
