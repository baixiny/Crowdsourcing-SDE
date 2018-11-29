<template>
	<div>
		<div class="top">
			<img src="../assets/icons/back.png" class="back" v-on:click="clickBack" >
			<label>修改个人信息</label>
			<label style="height:25px; width: 25px; background-color: #4F5D73 ; float: right"></label>
		</div>
		
		<div class="setinfoContainCss">
			<div class="infoItemCss">
				<img src="../assets/icons/sex.png" class="iconSetCss">
				<label class="labelCss">性别</label>
				<input type="radio" value="男" v-model="perInfo.sex" class="labelCss">
				<label for="one">男</label>
				<input type="radio" value="女" v-model="perInfo.sex" class="labelCss">
				<label for="two">女</label>	
				<!-- <input type="text" v-model=perInfo.sex class="inputCss">	 -->			
			</div>
			<div class="infoItemCss">
				<img src="../assets/icons/date.png" class="iconSetCss">
				<label class="labelCss">年龄</label>
				<!--<select>
					<option v-for="n in 120" class="inputCss">{{n}}</option>
				</select>-->
				<input type="number" v-model=perInfo.age class="inputCss" min="1" max="120">
			</div>
			<div class="infoItemCss">
				<img src="../assets/icons/address.png" class="iconSetCss">
				<label class="labelCss">地区</label><input type="text" v-model=perInfo.area class="inputCss">				
			</div>
			<div class="infoItemCss">
				<img src="../assets/icons/mail.png" class="iconSetCss">
				<label class="labelCss">电话</label><input type="number" v-model=perInfo.phoneno class="inputCss">				
			</div>
			<div class="infoItemCss">
				<img src="../assets/icons/hobits.png" class="iconSetCss">
				<label class="labelCss">技能</label><input type="text" v-model=perInfo.skill class="inputCss">
			</div>
			<button class="btnSubmitSetCss" @click = 'btnSure'>确认修改</button>	
		</div>
		</div>

	</div>
</template>
<script type="text/javascript">
	import global from '../Global'
	export default{
		name:'setter',
		data(){
			return{
				perInfo:'',
				page:'3',
				ages:[]
			}
		},
		mounted(){
			var self = this;
			self.perInfo = self.$route.query.perInfo;
			console.log(self.perInfo);
		},
		methods:{
			clickBack: function(){
				this.$router.push({path:'/home/personalinfo',query:{page:this.page}});
			},
			btnSure: function(){
				var self = this;
				this.$ajax({
			      	method: 'POST',
			      	url: global.urlUpdatePerInfo,
			      	data: {
				        /*username: this.username,*/
				    	/*perInfo:self.perInfo,*/
				    	username: self.perInfo.username,
				        password: self.perInfo.password,
				        phoneno: self.perInfo.phoneno,
				        reward: self.perInfo.reward,
				        sex: self.perInfo.sex,
				        age: self.perInfo.age,
				        area: self.perInfo.area,
				        skill: self.perInfo.skill
			      	}
			   	})
				.then(function (response) {
				    console.log(response);
				   	self.$router.push({path:'/home/personalinfo',query:{page:self.page}});
				})
				.catch(function (response) {
				    console.log(response);
				});
			}
		}
	}
</script>
<style type="text/css">
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
	.imgPhoto{
		margin-top: 40px;
		height: 80px;
		width: 80px;
	}
	.setinfoContainCss{
		margin-top: 20px;
		font-size: 14px;	
		height: 300px;
		color: #4F5D73;
	}
	.infoItemCss{		
		margin-top: 13px;
		text-align: left;
	}
	.inputCss{
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 1px;
		border-radius: 0;
		font-size: 14px;
		height: 25px;
		width: 60%;
		border-color: #BFBFBF;
		color: #4F5D73;
		margin-left: 10px;
		/* readonly: readonly; */
	}
	.iconSetCss{
		width: 20px;
		height: 20px;
		vertical-align:middle;
		padding-bottom: 5px;
		float: left;
		margin-left: 40px;
	}
	
	.btnSubmitSetCss {
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
		width: 80%;
		height: 35px;
		margin-top: 50px;
	}
	.btnSubmitSetCss:hover {
		background-color:#ffffff;
	}
	.btnSubmitSetCss:active {
		position:relative;
		top:1px;
	}
	.labelCss{
		margin-left: 10px;
	}
</style>