<template>
	<div>
		<div class="loginTop">移动众包实验平台</div>
		<div id='login'>
			<div>
				<img src="../assets/icons/user.png" class="iconLoginCss">
				<input type="text" class="inputLoginCss" v-model="username">
			</div>
			<div>
				<img src="../assets/icons/pw.png" class="iconLoginCss">
				<input type="password" v-model="password" class="inputLoginCss">
			</div>		
		</div>
		<div class="textMsg">
				{{msg}}
			</div>
		<div style="margin-top: 50px">
			<button v-on:click='clickLogin' class="btnLogin">登录</button>
			<button v-on:click="clickRegister" class="btnLogin">注册</button>
		</div>
		
	</div>
</template>

<script type="text/javascript">
	import global from '../Global'
	export default{
		name:'Login',
		data(){
			return{
				username:'',
				password:'',
				msg:''
			}
		},
		methods:{
			clickLogin: function(){
				if(this.username == ''){
					this.msg = '用户名不能为空！';
					return
				}
				if(this.password == ''){
					this.msg = '密码不能为空！';
					return
				}
				var self = this;
				this.$ajax({
			      	method: 'PUT',
			      	url: global.urlLogin,
			      	data: {
				        username: self.username,
				    	password: self.password
			      	}
			   	})
				.then(function (response) {
				    console.log(response.data.state);
				    if(response.data.state == 1){
				    	console.log('------------------登录成功，检查是否有问卷--------------');
				    	//登录成功
				    	localStorage.setItem('username',self.username);
				    	//判断是否有问卷
				    	//发送请求到服务器
				    	self.$ajax({
				    		method: 'PUT',
					      	url: global.urlGetQuestionnaire,
					      	data:{
					      		username: self.username
					      	}
				    	})
				    	//获取数据成功
				    	.then(function(response){
				    		/*console.log(response);*/
				    		if(response.data.state == 0){
				    			//有数据
				    			//有问卷，跳转到问卷页面
				    			console.log('------------------有问卷--------------');
				    			self.$router.push({path:'/preQuestionnaire',query:{ask:response.data.message[0]}});
				    		}else{
				    			console.log('-----------------没有问卷--------------');
				    			//没有问卷，跳转到主页面
				    			self.$router.push({path:'/home',query:{page:'1'}});
				    		}
				    	})
				    	//获取数据失败
				    	.catch(function(response){
				    		console.log(response);
				    	});
				    	
				    }else if(response.data.state == 2){
				    	self.msg = '用户名不存在:(';
				    }else if(response.data.state == 3){
				    	self.msg = '密码错误:(';
				    }
				    
				})
				.catch(function (response) {
				    console.log(response);
				    self.msg = '网络连接失败:(';
				});
				/*localStorage.setItem('username',this.username);*/
				/*this.$router.push('/home');*/
				/*this.$router.push({path:'/home',query:{page:'1'}});*/
				
			},
			clickRegister: function(){
				this.$router.push('/register')
			}
		}
	}
</script>

<style type="text/css">
	.loginTop{
		font-size:24px;
		margin-top: 100px;
	}
	#login{
		font-size: 16px;
		margin-top: 100px;
	}

	/* .btn{
		margin-top: 20px;
		width: 230px;
		height: 35px;
		font-size: 16px;
		border-style: solid;
		border-radius: 10px;
		border-width: 1px;
		border-color: #4F5D73;
		padding: 3px 20px;
		background-color: #FFFFFF;
	} */

	.btnLogin {
		background-color:#ffffff;
		-moz-border-radius:5px;
		-webkit-border-radius:5px;
		border-radius:5px;
		border:1px solid #4f5d73;
		display:inline-block;
		cursor:pointer;
		color:#4f5d73;
		font-family:Arial;
		font-size:14px;
		padding: 3px 20px;
		text-decoration:none;
		width: 230px;
		height: 35px;
		margin-top: 20px;
	}
	.btnLogin:hover {
		background-color:#ffffff;
	}
	.btnLogin:active {
		position:relative;
		top:1px;
	}

	.iconLoginCss{
		width: 30px;
		height: 30px;
		vertical-align:middle;
	}
	.inputLoginCss{
		border-left-width: 0px;
		border-right-width: 0px;
		border-top-width: 0px;
		border-bottom-width: 1px;
		margin-bottom: 10px;
		padding-bottom: 5px;
		border-radius: 0;
		font-size: 16px;
		height: 100%;
		width: 200px;
	}
	.textMsg{
		margin-top: 10px;
		font-size: 14px;
		color: red;
		height: 30px;
	}
</style>