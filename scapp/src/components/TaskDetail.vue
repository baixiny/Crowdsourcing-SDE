<template>
<div style="overflow: auto;">
	<div class="top">
		<img src="../assets/icons/back.png" class="back" v-on:click="clickBack" >
		<label>任务详情</label>
		<label style="height:25px; width: 25px; background-color: #4F5D73 ; float: right"></label>
	</div>
	<div style="padding-top: 45px"></div>  
	<div>
		<div class="divDetailCCss" style="margin-top:10px; padding-bottom:10px; border-bottom-width: 8px;">
			<label class="labTTtlDetailcss">{{task.description}}</label><br>
			<img src="../assets/icons/loc.png" class="iconDetailLocCss">
			<label style="font-size: 12px; color: #9E9E9E;">
				{{task.location}}&nbsp;&nbsp;
				距您当前位置&nbsp;{{task.distance}}&nbsp;米
			</label>
		</div>
		<div class="divDetailCCss">		
			<label class="labTtlDetailcss">问题</label>
			<div  v-for="item in task.qusetion"  class="divDetailContentCss">
				{{item.description}}
			</div>
		</div>
		<div class="divDetailCCss">		
			<label class="labTtlDetailcss">期望收到的回答数量</label>
			<label class="divDetailContentCss">{{task.answersnum}}</label>
		</div>
		<div class="divDetailCCss">
			<label class="labTtlDetailcss">当前进度</label>
			<label class="divDetailContentCss">{{task.schedule}}%</label>	 
		</div>		
		<div class="divDetailCCss">		
			<label class="labTtlDetailcss">截止时间</label>
			<label class="divDetailContentCss">{{task.deadline}}</label>
		</div>
	</div>
  	<div>
    <!-- 地图容器-->
	    <div class="divDetailCCss" style="border-bottom-width:0">	
			<label class="labTtlDetailcss">位置</label>
		</div>	
		<div id="amap-div" class="amap-wrapper"></div>
 	</div>
 	<div class="labTtlDetailcss" style="margin-top: 10px;">
 		<img v-if='hasAsk' src="../assets/icons/tips.png" class="iconTDetailCss">
 		<label class="divDetailContentCss">{{hasAskMsg}}</label>
 	</div>
 	<button class="btnDetailBottom" v-on:click="clickAccept">{{msg}}</button>
 	
</div>
</template>

<script>
import global from '../Global'
export default {
  	name: 'TaskDetail',
  	data () {
    	return {
    		task:'',
      		msg: '',
      		taskInfo:'',
      		quesArray:[],
      		hasAsk:false,
      		hasAskMsg:'',
      		ask:''
    	}
  	},
 	mounted(){
		var self = this;
		self.username = localStorage.getItem('username');
		self.task = self.$route.query.task;
		console.log(self.task.id);
		console.log(self.task.status);
		/*self.taskInfo=response.data.message;
        self.quesArray=response.data.message.question; 
        console.log(response.data.message.question);  */
	    if (self.task.status == '已完成') {
	        	self.msg = '已完成';
	    }
	    else if(self.task.username == self.username){
	    		self.msg = '查看回答';
	    }
	    else{
		var self = this;
        this.$ajax({
            method: 'PUT',
            url: global.urlGetTaskDetail,
            data: {
                tid: self.task.id,
                tname: self.username
             }
        })
	    .then(function (response) {
	        console.log(response.data.state);
	        console.log(response.data);

	        if(response.data.state == 1){
	        	self.msg = '可接受';
	            self.taskInfo=response.data.message;
	            self.quesArray=response.data.message.question; 
	            console.log(response.data.message.question);        
	              
	        }else if(response.data.state == 2){
	            self.taskInfo = '该任务无详情';
	        }else if(response.data.state == 0){
	        	self.msg = '已回答';
	        }           
	    })
	    .catch(function (response) {
	            console.log(response);
	            self.taskInfo = '网络连接失败:(';
	    });
		}
		console.log("mounted"+this.username);
		var mapObj = new AMap.Map('amap-div',{
		      //resizeEnable: true,
		    zoom: 15,
		    center: [116.480983, 40.0958]
		});
		mapObj.plugin(['AMap.ToolBar'], function () {
		    mapObj.addControl(new AMap.ToolBar())
		});
	  /* 设置地图类型和工具栏 
	  	mapObj.plugin(['AMap.ToolBar', 'AMap.MapType'], function () {
	      mapObj.addControl(new AMap.ToolBar())
	      mapObj.addControl(new AMap.MapType({showTraffic: false, showRoad: false}))
	    });*/
	    mapObj.plugin(['AMap.Geolocation'], function () {
	      	let geolocation = new AMap.Geolocation({
	            enableHighAccuracy: true, //  是否使用高精度定位，默认:true
	            timeout: 10000, //  超过10秒后停止定位，默认：无穷大
	            maximumAge: 0, // 定位结果缓存0毫秒，默认：0
	            convert: false, // 自动偏移坐标，偏移后的坐标为高德坐标，默认：true
	            showButton: true, //  显示定位按钮，默认：true
	            buttonPosition: 'LB',  // 定位按钮停靠位置，默认：'LB'，左下角
	            buttonOffset: new AMap.Pixel(10, 20), //  定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
	            showMarker: true, //  定位成功后在定位到的位置显示点标记，默认：true
	            showCircle: true, //  定位成功后用圆圈表示定位精度范围，默认：true
	            panToLocation: true,  //  定位成功后将定位到的位置作为地图中心点，默认：true
	            zoomToAccuracy: true  //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
	      	});
	      	mapObj.addControl(geolocation);
	      	geolocation.getCurrentPosition();
	    });

	    //将任务标记在地图上
	    var markers = [];
	    var marker;
    	marker = new AMap.Marker({
			position:[self.task.longitude,self.task.altitude],
			title: self.task.id, 
			map: mapObj
		});
		markers.push(marker);
		mapObj.setFitView();

		self.$ajax({
    		method: 'PUT',
	      	url: global.urlGetTaskQuestionnaire,
	      	data: {
	      		username: self.username,
	      		linkedtaskid: self.task.id
	      	}
    	})
    	//获取数据成功
    	.then(function(response){
    		/*console.log(response);*/
    		if(response.data.state == 0){
    			//有数据
    			//有问卷，跳转到问卷页面
    			console.log(response.data);
    			self.hasAsk = true;
    			self.hasAskMsg = '接受该任务需首先完成一个问卷调查。';
    			self.ask = response.data.message[0];
    		}
    	})
    	//获取数据失败
    	.catch(function(response){
    		console.log(response);
    	});

	  },
	  methods:{
	  	clickAccept: function(){
	  		/*self.$router.push({path:'/preQuestionnaire',query:{ask:response.data.message[0]}});*/
	  		if(this.hasAsk == true){
	  			this.$router.push({path:'/home/questionnaireWithTask',query:{task:this.task, ask: this.ask}});
	  		}else if(this.msg == '可接受'){
	  			this.$router.push({path:'/home/accepttask',query:{task:this.task}});
	  		}else if(this.msg == '查看回答'){
	  			this.$router.push({path:'/home/choose',query:{task:this.task}});
	  		}
	  		else{
	  			this.$router.push({path:'/home',query:{page:1}});
	  		}
		},
		clickBack: function(){
			this.$router.push({path:'/home',query:{page:1}});
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
		top: 0;
		height: 40px;
		width: 100%;
		font-size: 16px;
		position: fixed;
		z-index: 100;
	}
	.back{
		width:25px; 
		height:25px;
		float: left;
	}
	.amap-wrapper {
		margin: auto;
	  	width: 94%;
	  	height: 200px;
	  	border-style: solid;
	  	border-width: 1px;
	  	border-color: #EBEBEB;
	  	border-radius: 5px;
	  	margin-top: 5px;
	}
	.divDetailCCss{
		margin-top: 3px;
		font-size: 14px;
		text-align: left;
		border-style: solid;
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0;
		border-color: #EBEBEB;
		padding-left: 20px;
		padding-bottom: 3px;
	}
	.labTTtlDetailcss{
		font-size: 15px;
		font-weight: 550;
	}
	.taskIconCss{
		width: 23px;
		vertical-align: middle;
		padding-bottom: 3px;
	}
	.labTtlDetailcss{
		font-size: 14px;
		color: #9E9E9E;
		font-weight:900;
		padding: 0;
	}
	.divDetailContentCss{
		margin-left: 10px;
		font-size: 13px;
	}
	/* .bottom{
		width: 100%;
		position: fixed;
		bottom: 0;
		height: 50px;
		font-size: 16px;
		background-color: #FFFFFF;
	} */
	/* .btnBottom{
		width: 150px;
		height: 50px;
		background-color: #4F5D73;
		border:none;
		color: #FFFFFF;
	} */
	.btnDetailBottom {
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
		text-decoration:none;
		margin-top: 10px;
		width: 80%;
		height: 35px;
	}
	.btnDetailBottom:hover {
		background-color:#e7eaf0;
	}
	.btnDetailBottom:active {
		position:relative;
		top:1px;
	}

	.iconTDetailCss{
		width: 22px;
		height: 22px;
		vertical-align:middle;
	}

	.iconDetailLocCss{
		width: 13px;
		vertical-align: middle;
		margin-left: 0px;
	}

</style>