<template>
	<div>
		<div class="registerTop">注册</div>
		<div style="margin-top: 100px; font-size: 16px;">
			<div>
				<img src="../assets/icons/user.png" class="iconCss">
				<input type="text" name="" v-model='username' class="inputCss">
			</div>
			<div>
				<img src="../assets/icons/pw.png" class="iconCss">
				<input type="password" name="" v-model='pw1' class="inputCss">
			</div>
			<div>
				<img src="../assets/icons/pw.png" class="iconCss">
				<input type="password" name="" v-model='pw2' class="inputCss">
			</div>
			
			<div class="textMsg">
				{{msg}}
			</div>

			<div style="margin-top: 50px">
				<button class="btn" v-on:click="clickRegister">注册</button>
				<button class="btn" v-on:click="clickBack">返回</button>
			</div>
		</div>
	</div>
</template>
<script type="text/javascript">
	import global from '../Global'
	export default{
		name:'Register',
		data(){
			return{
				username:'',
				pw1:'',
				pw2:'',
				msg:'用户名不能为空!',
			}
		},
		watch :{
			username: function(newUsername,oldUsername){
				if(newUsername == ''){
					this.msg = '用户名不能为空!';
				}else{
					this.msg = '';
				}		
			},
			pw1: function(newPw1,oldPw1){
				if(newPw1 == ''){
					this.msg = '密码不能为空!';
				}else{
					this.msg = '';
				}		
			},
			pw2: function(newPw2,oldPw2){
				if(newPw2 == ''){
					this.msg = '密码不能为空!';
				}else if(newPw2 != this.pw1){
					this.msg = '两次输入的密码不一致！';
				}else {
					this.msg = '';
				}			
			}
		}
		,
		methods:{
			clickBack: function(){
				this.$router.replace('/');
			},
			clickRegister: function(){
				var self = this;
				if(this.username == ''){
					this.msg = '用户名不能为空！';
					return 
				}else if(this.pw1 == '' || this.pw2 == ''){
					this.msg = '密码不能为空！';
					return 
				}else if(this.pw1 != this.pw2){
					this.msg = '两次输入的密码不一致！';
					return
				} 
				this.$ajax({
			      	method: 'POST',
			      	url: global.urlRegister,
			      	data: {
				        username: self.username,
				    	password: self.pw1
			      	}
			   	})
				.then(function (response) {
				    console.log(response.data.state);
				    if(response.data.state == 2){
				    	self.msg = '该用户名已被使用';
				    }else{
				    	self.$router.replace('/');
				    }		    
				})
				.catch(function (response) {
				    console.log(response);
				});
			}
		}
	}
</script>

<style type="text/css">
	.registerTop{
		font-size:24px;
		margin-top: 100px;
	}
	.btn {
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
	.btn:hover {
		background-color:#ffffff;
	}
	.btn:active {
		position:relative;
		top:1px;
	}

	.iconCss{
		width: 30px;
		height: 30px;
		vertical-align:middle;
	}
	.inputCss{
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