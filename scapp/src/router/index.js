import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Home from '@/components/Home'
import PersonalInfo from '@/components/PersonalInfo'
import History from '@/components/History'
import TaskList from '@/components/TaskList'
import AcceptTask from '@/components/AcceptTask'
import Setter from '@/components/Setter'
import Detail from '@/components/Detail'
import TaskDetail from '@/components/TaskDetail'
import Questionnaire from '@/components/Questionnaire'
import PreQuestionnaire from '@/components/PreQuestionnaire'
import QuestionnaireWithTask from '@/components/QuestionnaireWithTask'
import AddTask from '@/components/AddTask'
import GetPos from '@/components/GetPos'
import ChooseAnswer from '@/components/ChooseAnswer'


Vue.use(Router)

const routes = [
	{path: '/', component: Login},
	{path: '/register', component: Register},
	{path: '/detail', component: Detail},
	{path: '/home',component: Home,
		children:[
		{
			path:'/home',
			component: TaskList/*,
			children:[{
				path:'/home/accepttask',
				component: AcceptTask	
			}]*/
		},
		{
			path:'/home/addTask',
			component:AddTask
		},
		{
			path:'/home/personalinfo',
			component: PersonalInfo
		},
		
		]
	},
	{path:'/home/history',component: History},
	{path:'/home/taskdetail',component: TaskDetail},
	{path:'/home/accepttask',component: AcceptTask},
	{path:'/home/setter',component: Setter},
	{path:'/home/getpos',component: GetPos},
	{path:'/home/choose',component: ChooseAnswer},
	{path:'/questionnaire',component: Questionnaire},
	{path:'/preQuestionnaire',component: PreQuestionnaire},
	{path:'/home/questionnaireWithTask',component: QuestionnaireWithTask},
]

var router = new Router({
	routes
})


export default router;
