<template>
<div>
	<router-view class="container"></router-view>
	<div class="bottom">
		<button v-bind:class="cTask" v-on:click="clickTask">查看任务</button>
		<button v-bind:class="cAddTask" v-on:click="clickAddTask">发布任务</button>
		<!--<button v-bind:class="cHistory" v-on:click="clickHistory">我的回答</button>-->
		<button v-bind:class="cPerInfo" v-on:click="clickPerInfo">个人信息</button>
	</div>
</div>
</template>
<script type="text/javascript">
	export default{
		name:'Home',
		data(){
			return{
				//从登陆页面获取用户名
				username:'',
				cTask: 'clicked',
				//cHistory: 'unclicked',
				cPerInfo: 'unclicked',
				cAddTask: 'unclicked',
				page: '',

			}
		},
		mounted: function(){			
			this.username = localStorage.getItem('username');
			this.page = this.$route.query.page;

			console.log(this.username);
			if(this.page == '1'){
				this.cTask = 'clicked';
				//this.cHistory = 'unclicked';
				this.cPerInfo = 'unclicked';
				this.cAddTask = 'unclicked';
			}else if(this.page == '2'){
				this.cAddTask = 'clicked';
				//this.cHistory = 'unclicked';
				this.cTask = 'unclicked';
				this.cPerInfo = 'unclicked';
				
			}else if(this.page == '3'){
				this.cPerInfo = 'clicked';
				this.cTask = 'unclicked';
				//this.cHistory = 'unclicked';
				this.cAddTask = 'unclicked';
			}/*
			else if(this.page == '4'){
				this.cHistory = 'clicked';
				this.cAddTask = 'unclicked';			
				this.cTask = 'unclicked';
				this.cPerInfo = 'unclicked';
			}*/
		},
		methods:{
			clickTask: function(){
				//this.$router.push('/home/');
				this.$router.push('/home/');
				console.log(this.$route.path);
				if(this.cTask == 'unclicked'){					
					this.cTask = 'clicked';
					this.cAddTask = 'unclicked';
					this.cPerInfo = 'unclicked';
					//this.cHistory = 'unclicked';
				}
			},
			clickAddTask: function(){
				//this.$router.push('/home/history');
				this.$router.push('/home/addTask');
				console.log(this.$route.path)
				if(this.cAddTask == 'unclicked'){
					this.cAddTask = 'clicked';
					this.cTask = 'unclicked';
					this.cPerInfo = 'unclicked';
					//this.cHistory = 'unclicked';
				}
			},
			clickPerInfo: function(){
				this.$router.push('/home/personalinfo');
				console.log(this.$route.path)
				if(this.cPerInfo == 'unclicked'){
					this.cPerInfo = 'clicked';
					this.cTask = 'unclicked';
					this.cAddTask = 'unclicked';
					//this.cHistory = 'unclicked';
				}
			},
			/*clickHistory: function(){
				this.$router.push('/home/history');
				console.log(this.$route.path);
				if(this.cHistory == 'unclicked'){					
					this.cHistory = 'clicked';
					this.cAddTask = 'unclicked';
					this.cPerInfo = 'unclicked';
					this.cTask = 'unclicked';
				}

			}*/
			
		}
	}
</script>

<style type="text/css">
	.bottom{
		width: 100%;
		position: fixed;
		bottom: 0;
		height: 50px;
		font-size: 14px;
		background-color: #4F5D73;
	}
	.container{
		bottom: 50px;
		position: fixed;
		top: 0px;
		left: 0px;
		right: 0px;
	}
	.unclicked{
		width: 100px;
		height: 50px;
		background-color: #4F5D73;
		border:none;
		color: grey;
	}
	.clicked{
		width: 100px;
		height: 50px;
		background-color: #4F5D73;
		border:none;
		color: #FFFFFF;
	}

	
</style>