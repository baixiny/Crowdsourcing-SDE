// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'iview/dist/styles/iview.css'
import store from './store';
import iView from 'iview';
// 按需加载iview组件
// import { Button, 
//   Avatar, 
//   Dropdown, 
//   DropdownItem, 
//   DropdownMenu, 
//   Icon, 
//   Form, 
//   FormItem, 
//   Input,
//   Menu,
//   MenuGroup,
//   MenuItem, 
//   AutoComplete,
//   InputNumber,
//   Row,
//   Col,
//   Option,
//   Alert,
//   Table,
//   Modal
// } from 'iview';


// Vue.component('Button', Button);
// Vue.component('Avatar', Avatar);
// Vue.component('Dropdown', Dropdown);
// Vue.component('DropdownItem', DropdownItem);
// Vue.component('DropdownMenu', DropdownMenu);
// Vue.component('Icon', Icon);
// Vue.component('Form', Form);
// Vue.component('FormItem', FormItem);
// Vue.component('Input', Input);
// Vue.component('Menu', Menu);
// Vue.component('MenuItem', MenuItem);
// Vue.component('MenuGroup', MenuGroup);
// Vue.component('AutoComplete', AutoComplete);
// Vue.component('InputNumber', InputNumber);
// Vue.component('Row', Row);
// Vue.component('Col', Col);
// Vue.component('Option', Option);
// Vue.component('Alert', Alert);
// Vue.component('Table', Table);
// Vue.component('Modal', Modal);

Vue.use(iView);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
