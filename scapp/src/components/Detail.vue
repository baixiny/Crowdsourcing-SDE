<!-- Detail.vue -->
<template>
<div style="overflow: auto;">
	<div  class="top">
		<img src="../assets/icons/back.png" class="back" v-on:click="clickBack" >
		<label>答案详情</label>
		<label style="height:25px; width: 25px; background-color: #4F5D73 ; float: right"></label>
	</div>
	<div style="padding-top: 45px"></div> 
	<div style="text-align: left">
			<div class="titleCss">主题：{{info.task_description}}</div>
		<div style="width: 90%; margin: auto;">
			<div>
				<label class="itemTitleCss">任务类型</label>
				<label class="itemContentCss">{{info.task_tasktype}}</label>
			</div>
			<div>
				<label class="itemTitleCss">当前状态</label>
				<label class="itemContentCss">{{info.task_status}}</label>
			</div>
			<div>
				<label class="itemTitleCss">发布时间</label>
				{{info.task_publictime}}
			</div>
			<div>
				<label class="itemTitleCss">发布地点</label>
				{{info.task_location}}
			</div>
			<div>
				<label class="itemTitleCss">答案详情</label>
			</div>
			<ul class="ulAnswerCss">
				<li v-for="answer in info.answer">									    
		          	<div style="margin-left: 10px;">
			          	<div>{{answer.qid}}:{{answer.description}}</div>
			          	<div>您的回答：{{ answer.labeldescription}}</div>          
			        </div>    
				</li>
			</ul>
			<div>
				<label class="itemTitleCss">回答地点</label>
				<label class="itemContentCss">{{info.answer_location}}</label>
			</div>
			<div>
				<label class="itemTitleCss">回答时间</label>
				<label class="itemContentCss">{{info.answertime}}</label>
			</div>
			<div  class="itemTitleCss">相关照片</div>
			<div style="text-align: center">
				<div v-for="pic in info.picpath">
					<img class='picCss' :src=pic > 
				</div>
			</div>
		</div>
	</div>
</div>
</template>

<script>
	import global from '../Global'
   	export default{

		name:'Detail',
		data () {
			return {
				tid:'',
				username:'',
				info: ''
			}
		},

		mounted(){
			var self = this;
			self.tid = self.$route.query.item.tid;
			self.username = localStorage.getItem('username');

			console.log('------------------历史记录详情页--------------');
			console.log('任务ID   ' + self.tid);
			console.log('用户名   ' + self.username);

			this.$ajax({
		      	method: 'PUT',
		      	url: global.urlHistoryDetail,
		      	data: {
			        username: self.username,
			        tid:self.tid
		      	}
		   	})
			.then(function (response) {
			    /*console.log(response.data.state);*/	    
			    if(response.data.status == 0){
			    	self.info = response.data.message[0];
			    	console.log(self.info);
			    }
			})
			.catch(function (response) {
			    console.log(response);
			});
		},

		methods:{
				clickBack: function(){
					this.$router.push({path:'/home/history',query:{page:2}});
				}		
			  }
    }
</script>

<style scoped>
	.top{
		/* display: flex; */
		text-align:center;
		padding: 10px;
		background-color: #4F5D73;
		color: #FFFFFF;
		font-size: 16px;
		position: fixed;
		z-index: 100;
	}
	.back{
		width:25px; 
		height:25px;
		float: left;
	}
	.picCss{
		width:80%; 
	}

	.titleCss{
		font-size: 15px;
		font-weight: 600;
		text-align: center;
		border-style: solid;
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0;
		border-color: #4F5D73;
		width: 90%;
		margin: auto;
		margin-top: 10px;
		padding-bottom: 5px;
	}

	.ulAnswerCss{
		list-style: none;
		overflow-y: auto;
	}

	.itemTitleCss{
		font-size: 14px;
		font-weight: 600;
		color: #9E9E9E
	}

	.itemContentCss{
		font-size: 13px;
		color: #000000;
	}

	
</style>
