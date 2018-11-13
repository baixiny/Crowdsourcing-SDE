import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/components/Login';
import Dashbord from '@/components/Dashbord';
import AddTask from '@/components/AddTask';
import TaskList from '@/components/TaskList';
import AnswerList from '@/components/AnswerList';
import AddQuestionnaire from '@/components/AddQuestionnaire';
import UserList from '@/components/UserList';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/dashbord',
      name: 'Dashbord',
      component: Dashbord,
      children: [
        {
          path: 'add_task',
          component: AddTask
        },
        {
          path: 'list_task',
          component: TaskList
        },
        {
          path: 'answer_list',
          component: AnswerList
        },
        {
          path: 'add_questionnaire',
          component: AddQuestionnaire
        },
        {
          path: 'user_list',
          component: UserList
        }
      ]
    }
  ]
})



