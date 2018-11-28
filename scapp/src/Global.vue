<script type="text/javascript">

const url = 'http://211.87.239.31:8080/Spring4MVCCRUDRestService';

const urlLogin = url+'/worker/login';
const urlRegister = url+'/worker/register';
const urlGetTaskList = url+'/task/gettask';
const urlGetTaskDetail = url+'/task/accepttask';
const urlUploadPic = url+'/savephoto';
const urlUploadAnswer = url+'/answer/add';
const urlGetPerInfo = url+'/worker/personinfo';
const urlUpdatePerInfo = url+'/worker/update';	//Setter.vue
const urlGetHistory = url+'/history/sketch';
/*const urlHistoryDetail = url+'/history/detail';*/
const urlHistoryDetail = url+'/answer/detail';
const urlGetQuestionnaire = url+'/question/ask';
const urlUploadQuesnaire = url +'/question/answer';
const urlGetTaskQuestionnaire = url + '/question/askone';
const urlAddTask = url + '/task/addone';
//const urlChooseAnswer = url + '/choose/answer'



export default{
	urlLogin,
	urlRegister,
	urlGetTaskList,
	urlGetTaskDetail,
	urlUploadPic,
	urlUploadAnswer,
	urlGetPerInfo,
	urlUpdatePerInfo,
	urlGetHistory,
	urlHistoryDetail,
	urlGetQuestionnaire,
	urlUploadQuesnaire,
	urlGetTaskQuestionnaire,
	urlAddTask,
	//urlChooseAnswer,

}
</script>