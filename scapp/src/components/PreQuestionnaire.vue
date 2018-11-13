<template>
	<div>
		<div style=" margin-top: 160px;">
			<img src="../assets/photos/wenjuan.png" style="height: 100px;">
		</div>
		<div>您有一份新的问卷，请查收...</div>
		<button class="btnCheck" @click = 'btnSure'>查看</button>
		<button class="btnCheck" @click = 'clickPass'>跳过</button>
	</div>
</template>
<script type="text/javascript">
	export default{
		name:'preQuestionnaire',
		data(){
			return{
				ask: '',
			}
		},
		mounted(){
			this.ask = this.$route.query.ask;
		},
		methods:{
			btnSure:function(){
				this.$router.push({path:'/questionnaire',query:{ask:this.ask}});
			},
			clickPass: function(){
				this.$router.push({path:'/home',query:{page:'1'}});
			}
		}
	}

</script>
<style type="text/css">
	.btnCheck {
		background-color:#ffffff;
		-moz-border-radius:5px;
		-webkit-border-radius:5px;
		border-radius:5px;
		border:1px solid #4f5d73;
		display:inline-block;
		cursor:pointer;
		color:#4f5d73;
		font-family:Arial;
		font-size:12px;
		padding: 3px 20px;
		text-decoration:none;
		width: 30%;
		height: 35px;
		margin: 50px 5px;
	}
	.btnCheck:hover {
		background-color:#ffffff;
	}
	.btnCheck:active {
		position:relative;
		top:1px;
	}
</style>