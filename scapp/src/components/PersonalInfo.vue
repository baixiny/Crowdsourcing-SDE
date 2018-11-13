<template>
	<div style="font-size: 14px; background-color: #F5F5F5">
		<!-- <div class="top">			
			<label class="lbl_top">我</label>
		</div> -->
		<div style="background-color:#4F5D73; padding-bottom: 20px">
			<img class="imgPhoto" src="../assets/photos/photo3.png">
			<div style="font-size: 18px; margin-top: 15px; color: #FFFFFF">
				<label>Hello, {{username}}</label>
			</div>
		</div>

		<!-- <div style="height:3px; width:100%; background-color: #4F5D73; margin-top:15px;"></div>
		 -->
		<br>
		<div class="infoContainCss">
			<div class="infoPerItemCss">
				<img src="../assets/icons/sex.png" class="iconPerInfoCss">
				<label class="lblPerInfpCss">性别</label>	
				<label class="lbItemValueCss">{{perInfo.sex}}</label>
			</div>

			<div class="infoPerItemCss">
				<img src="../assets/icons/date.png" class="iconPerInfoCss">
				<label class="lblPerInfpCss">年龄</label>	
				<label class="lbItemValueCss">{{perInfo.age}}</label>			
			</div>

			<div class="infoPerItemCss">
				<img src="../assets/icons/address.png" class="iconPerInfoCss">
				<label class="lblPerInfpCss">地址</label>
				<label class="lbItemValueCss">{{perInfo.area}}</label>	
			</div>

			<div class="infoPerItemCss">
				<img src="../assets/icons/mail.png" class="iconPerInfoCss">
				<label class="lblPerInfpCss">联系方式</label>	
				<label class="lbItemValueCss">{{perInfo.phoneno}}</label>			
			</div>

			<div class="infoPerItemCss">
				<img src="../assets/icons/hobits.png" class="iconPerInfoCss">
				<label class="lblPerInfpCss">技能特长</label>
				<label class="lbItemValueCss">{{perInfo.skill}}</label>	
			</div>

			<br>
				
			<div class="infoPerItemCss">
				<img src="../assets/icons/任务.png" class="iconPerInfoCss">
				<label class="lblPerInfpCss">历史记录</label>		
			</div>
			<div class="infoPerItemCss">
				<img src="../assets/icons/reward.png" class="iconPerInfoCss">
				<label class="lblPerInfpCss">奖励金</label>		
				<label class="lbItemValueCss">{{perInfo.mail}}</label>		
			</div>
			<br>
			<div class="infoPerItemCss" @click="clickSetBtn">
				<img src="../assets/icons/setPerInfo.png" class="iconPerInfoCss">
				<label class="lblPerInfpCss">设置</label>
			</div>
		</div>
	</div>
</template>
<script type="text/javascript">
	import global from '../Global'
	export default{
		name:'personalinfo',
		data(){
			return{
				username:'',
				perInfo:''
			}
		},
		mounted(){
			var self = this;
			self.username = localStorage.getItem('username');
			console.log(self.username);

			this.$ajax({
			      	method: 'PUT',
			      	url: global.urlGetPerInfo,
			      	data: {
				        username: self.username
			      	}
			   	})
				.then(function (response) {
				    console.log(response.data.state);
				    console.log(response.data.message[0]);
				    if(response.data.state == 1){
				    	self.perInfo = response.data.message[0];
				    }
				})
				.catch(function (response) {
				    console.log(response);
				});

			/*var info = {username: self.username,sex:'女',age:'21',address:'山东省济南市历下区',mail:'123456@163.com', skill:'摄影'};
			self.perInfo = info;*/

		},
		methods: {
			clickSetBtn: function(){
				this.$router.push({path:'/home/setter',query:{perInfo:this.perInfo,page:'3'}});
			}
		}
	}
</script>
<style type="text/css">
	.top{
		padding: 10px;
		background-color: #4F5D73;
		font-size: 14px;
		color: #FFFFFF;
	}
	.lbl_top{
		font-size: 14px;
	}
	.imgPhoto{
		margin-top: 50px;
		height: 80px;
		width: 80px;
	}
	.infoContainCss{
		font-size: 14px;	
		height: 300px;
		color: #4F5D73;
		text-align: left;
	}


	.infoPerItemCss{		
		text-align: left;
		border-width: 1px;
		/* margin-left: 20px; */
		padding: 5px 10px;
		font-size: 13px;
		border-style: solid;
		border-bottom-width: 1px;
		border-top-width: 1px;
		border-left-width: 0;
		border-right-width: 0;
		border-color: #DCDCDC;
		background-color: #FFFFFF;
		color: #000000;
		margin-top: -1px;
	}
	.infoPerItemCss:hover {
		background-color:#ffffff;
	}
	.infoPerItemCss:active {
		position:relative;
		top:1px;
	} 


	.iconPerInfoCss{
		width: 18px;
		vertical-align:middle;
		margin-left: 20px;
	}
	/* .btnSetCss{
		height: 30px;
		width: 200px;
		margin-top: 30px;
		border-width: 1px;
		border-color: #4F5D73;
		border-radius: 3px;
		font-size: 14px;
		color: #4F5D73;
		background-color: #FFFFFF;
	} */
	/* .btnSetCss {
		background-color:#ffffff;
		-moz-border-radius:5px;
		-webkit-border-radius:5px;
		border-radius:5px;
		border:0 solid #DCDCDC;
		border-bottom-width: 1px;
		border-top-width: 1px;
		display:inline-block;
		cursor:pointer;
		color:#4f5d73;
		font-family:Arial;
		font-size:14px;
		padding: 10px 20px;
		text-decoration:none;
		width: 100%;
		margin-top: 50px;
	}
	.btnSetCss:hover {
		background-color:#ffffff;
	}
	.btnSetCss:active {
		position:relative;
		top:1px;
	} */
	.lblPerInfpCss{
		margin-left: 5px;
		vertical-align: middle;
		font-size: 14px;
		color: #4F5D73;
	}

	.lbItemValueCss{
		vertical-align: middle;
		font-size: 14px;
		float: right;
		margin-right: 25px;
		color:  #4F5D73;
	}
</style>