<template>
	<div style="overflow: auto; background-color: #F5F5F5">
		<div class="top">
			<img src="../assets/icons/back.png" class="back" v-on:click="clickBack" >
			<label>我的回答</label>
			<label style="height:25px; width: 25px; background-color: #4F5D73 ; float: right"></label>
		</div>
		<div style="padding-top: 45px"></div> 
		<div class="content">
			<div v-if="seen" class="note">
			   没有历史任务
			  <p hidden="hidden">用户名：{{username}}</p> 
			</div>
			<ul class="cont_ul">
				<li v-for="item in items" class="list" @click="goDetail(item)" >					
				    <div class="historyBox">
					      <div class="imgWrapCss">
					      	 <img class="imgHisListCss" :src="item.icon" >
				          </div>
				          <div class="taskHisListconcent">
					          <label class="taskTitleCss">{{item.title}}</label><br>		
					          <label class="option">地址：{{ item.location }}</label><br>
					          <label class="time">回答时间：{{ item.time }}</label>					         
				         </div>
			        </div>
			     
				</li>
			</ul>
		</div>
	</div>
</template>
<script>
	import global from '../Global'
	export default {
		data () {
			return {
				username:'',
				seen:'',
				items: [],
				//tid:20
			}
		} ,
        
		mounted(){
			var self = this;
			self.username = localStorage.getItem('username');
			console.log('------------------历史记录-----------------');

			this.$ajax({
			      	method: 'PUT',
			      	url: global.urlGetHistory,
			      	//url: global.urlChooseAnswer,
			      	data: {
				        username: self.username
				        //tid:self.tid
			      	}
			   	})
				.then(function (response) {
				    console.log(response.data.message);
				    if(response.data.state == 1){
				    	self.items = response.data.message;
				    	self.seen=false;
				    }else if(response.data.state == 2){
				    	self.seen=true;
				    }
				})
				.catch(function (response) {
				    console.log(response);
				});

			
		},

	    methods:{
	    	goDetail: function(item){
	    		console.log('-----------点击历史记录某一条数据-----------');
				this.$router.push({path:"/Detail",query: {item:item} }) 
			},
			clickBack: function(){
				this.$router.push({path:'/home',query:{page:1}});
			}
		} 
			
	 }

</script>

<style type="text/css">
	.top{
		text-align:center;
		padding: 10px;
		background-color: #4F5D73;
		color: #FFFFFF;
		top: 0;
		height: 40px;
		width: 100%;
		font-size: 16px;
		position: fixed;
		z-index: 100;
	}
	.content{
		position:relative;
		text-align: left;
	}

	.note{
		font-size: 14px;
	}

    .list { 
        margin: 0 1px;
        padding: 2px 0;
	}

	.historyBox {
		background-color: white;
		padding: 5px 20px 5px;
		display: flex;
		border-style: solid;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 1px;
		border-top-width: 1px;
		border-color: #DCDCDC;
		margin-top: 5px;
	}

	.imgWrapCss{
		width:80px;
		height:80px;
	}

	.imgHisListCss{
		max-width: 80px;
		max-height: 80px;
	}
	.taskHisListconcent{
	    background-color: white;
        padding-left: 15px;     
        text-align: left;  
	}

	.option {
		font-size: 12px;
		color: #333;
	}
	.time {
		text-align:left;
		line-height: 0.5rem;
		font-size: 11px;
		color: #fb3b3b;
	}
    .taskTitleCss{     	
        font-size: 13px;
        font-weight: 700;
    }
    .cont_ul{
    	list-style: none;
    	overflow-y: auto;
		overflow: auto;
    }
    .back{
		width:25px; 
		height:25px;
		float: left;
	}
           
</style>