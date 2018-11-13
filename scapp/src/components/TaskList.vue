<template>
	<div style="background-color: #F5F5F5; overflow: auto">
		<div class="top">
			<label style="height:25px; width: 25px; background-color: #4F5D73 ; float: left"></label>
			任务列表
			<img src="../assets/icons/search.png" class="searchBtn" v-on:click="" >
		</div>
		<!-- <input id='keyword' type="text" name="keyword" v-model="searchKey" class="inputSearch" onfocus='this.value=""'>
			<button class="btnSearch" @click="clickSearch">查询</button> -->
		<!-- 地图容器  class="amap-wrapper" -->
		<div id="amap-div"></div>
		<div>
			<div class="divMsgTLCss">
				<!-- <img src="../assets/icons/tips.png" class="iconCss"> -->
				<img src="../assets/icons/loc.png" class="taskIconCss">
				<label>{{msg}}</label>
			</div>
			<ul class="ulCss" >
				<li v-for="task in tasks">
					<div class="liTaskListDivCss"  v-on:click='clickTask(task)'>
						<!-- <img src="../assets/icons/tasks.png" style="height: 20px; vertical-align: middle; padding-bottom: 5px"> -->
						<!-- <label class="lable-title-css">{{task.id}}</label> -->
						<!-- <label style="font-weight: 550; font-size: 15px; ">标题</label> -->
						<label class="labTaskListTtlCss">{{task.description}}</label>
						<div>
							<img src="../assets/icons/loc.png" class="taskIconCss">
							<label>任务地点</label><label style="color:#4F5D73">&nbsp;&nbsp;{{task.location}}</label>
							<label style="color:#4F5D73">距您{{task.distance}}米</label>
						</div>
						
						<div>	
							<img src="../assets/icons/deadline.png" class="taskIconCss">
							<label>截止日期</label><label style="color:#4F5D73">&nbsp;&nbsp;{{task.deadline}}</label>
						</div>			
						<div>
							<img src="../assets/icons/process.png" class="taskIconCss">
							<label>当前进度</label><label style="color:#4F5D73">&nbsp;&nbsp;{{task.schedule}}%</label>	
							<!-- <progress id=schedule value="" max="100"></progress>
							<label style="color:#4F5D73">%</label> -->
							<div class="progressContainer">  
							     <div class="progress" :style="{width:task.schedule+'%'}">  
							          <!-- <b>{{progress}}%</b>   -->
							     </div>  
							</div>  
						</div>		
					</div>
				</li>
			</ul>
		</div>
		
	</div>
</template>
<script type="text/javascript">
	import AMap from 'AMap'
	import global from '../Global'
	export default{
		name:'tasklist',
		data(){
			return{
				tasks:[],
				username:'',
				searchKey: '',
				msg:"",
				progress:0
			}
		},
		mounted(){
			var self = this;
			self.username = localStorage.getItem('username');
			

			//发送post请求，加载任务信息
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
	          	AMap.event.addListener(geolocation, 'complete', (result) => {
		            mapObj.setCenter(result.position)
		            console.log(result.position);
		            //
		            //将获取到的经纬度发送至服务器端，计算得到附近的任务信息，根据任务的经纬度在地图上标注
		            //

		            localStorage.setItem('longitude',result.position.lng);
		            localStorage.setItem('latitude',result.position.lat);

		            self.$ajax({
				      	method: 'PUT',
				      	url: global.urlGetTaskList,
				      	data: {
					        longitude:result.position.lng,
							latitude:result.position.lat
				      	}
					})
					.then(function (response) {
						     /*console.log(response.data.state);*/
						    console.log(response.data.message);
						    if(response.data.state == 1){
						    	//返回了任务信息
						    	self.tasks = response.data.message;						    	
						    	var ts = self.tasks;

						    	 //处理task的参数以便于展示
						    	for(var i=0; i<ts.length; i++){
						    		//将时间毫秒转化为日期
									/*var unixTimestamp = new Date(ts[i].deadline) ;
									ts[i].deadline = unixTimestamp.toLocaleString();*/
									//将完成度取整
									if(ts[i].schedule.indexOf("%"))	{
										var sche = ts[i].schedule.split("%")[0].split(".")[0];
										/*ts[i].schedule = sche;*/
										ts[i].schedule = sche;
									}				
						    	}    

						 		/*var markers = [];
					            for(var i = 0; i<ts.length; i+=1){
					            	console.log(ts[i].longitude,ts[i].altitude);
					            	var marker;
					            	marker = new AMap.Marker({
										position:[ts[i].longitude,ts[i].altitude],
										title: ts[i].id, 
										map: mapObj
									});
									markers.push(marker);
					            }
					            mapObj.setFitView();*/
					         
						    }else{
						    	self.msg = '暂时没有可接受的任务 :(';
						    }	
					})
					.catch(function (response) {
						    console.log(response);
					});

		            /*var ts = [
						{id: 'task1',loc:'丁豪广场',dis:'3.2km',pos:[116.9858,36.6664]},
						{id: 'task2',loc:'美莲广场',dis:'1.2km',pos:[116.9648,36.6564]}];*/

					/*self.tasks = ts;*/

					//需要把task的经纬度改成position格式
					var ts = self.tasks;
		            var markers = [];
		            for(var i = 0; i<ts.length; i+=1){
		            	var marker;
		            	marker = new AMap.Marker({
							position: ts[i].pos,
							title: ts[i].id,
							map: mapObj
						});
						markers.push(marker);
		            }
		            mapObj.setFitView();
		        })  //  返回定位信息
		        AMap.event.addListener(geolocation, 'error', (result) => {
		            console.log(result);
		            self.msg  = '定位失败:(';

		            localStorage.setItem('longitude',117.144529);
		            localStorage.setItem('latitude',36.672898);

		            self.$ajax({
				      	method: 'PUT',
				      	url: global.urlGetTaskList,
				      	data: {
					        longitude:117.144529,
							latitude:36.672898
				      	}
					})
					.then(function (response) {
						    /*console.log(response.data.state);*/
						    console.log(response.data.message);
						    if(response.data.state == 1){
						    	//返回了任务信息
						    	self.tasks = response.data.message;						    	
						    	var ts = self.tasks;

						    	 //处理task的参数以便于展示
						    	for(var i=0; i<ts.length; i++){
						    		//将时间毫秒转化为日期
									/*var unixTimestamp = new Date(ts[i].deadline) ;
									ts[i].deadline = unixTimestamp.toLocaleString();*/
									//将完成度取整
									if(ts[i].schedule.indexOf("%"))	{
										var sche = ts[i].schedule.split("%")[0].split(".")[0];
										/*ts[i].schedule = sche;*/
										ts[i].schedule = sche;
									}				
						    	}    

						 		/*var markers = [];
					            for(var i = 0; i<ts.length; i+=1){
					            	console.log(ts[i].longitude,ts[i].altitude);
					            	var marker;
					            	marker = new AMap.Marker({
										position:[ts[i].longitude,ts[i].altitude],
										title: ts[i].id, 
										map: mapObj
									});
									markers.push(marker);
					            }
					            mapObj.setFitView();*/
					         
						    }else{
						    	self.msg = '暂时没有可接受的任务 :(';
						    }					
					})
					.catch(function (response) {
						    console.log(response);
					});


		        })  //  返回定位出错信息
		    });


			AMap.service('AMap.Geocoder',function(){//回调函数
			    //实例化Geocoder
			    var geocoder = new AMap.Geocoder({
			        city: "010"//城市，默认：“全国”
			    });
			    //TODO: 使用geocoder 对象完成相关功能

			    //逆地理编码
				var lnglatXY=[localStorage.getItem('longitude'), localStorage.getItem('latitude')];//地图上所标点的坐标
				geocoder.getAddress(lnglatXY, function(status, result) {
				    if (status === 'complete' && result.info === 'OK') {
				       //获得了有效的地址信息:
				       //即，result.regeocode.formattedAddress
				       console.log(result.regeocode.formattedAddress);
				       localStorage.setItem('location',result.regeocode.formattedAddress);
				       self.msg = result.regeocode.formattedAddress;
				    }else{
				       //获取地址失败
				       console.log('获取地址失败  :(');
				    }
				}); 
			})
			 
		   /* mapObj.plugin(['AMap.Autocomplete','AMap.PlaceSearch'],function(){
		      	var autoOptions = {
			        city: "济南", //城市，默认全国
			        input: "keyword"//使用联想输入的input的id
		   		};
			    var autocomplete= new AMap.Autocomplete(autoOptions);
			    var placeSearch = new AMap.PlaceSearch({
			        city:'济南',
			        map:mapObj
		    	});
		      	AMap.event.addListener(autocomplete, "select", function(e){
		        	 //TODO 针对选中的poi实现自己的功能
		         	/*placeSearch.setCity(e.poi.adcode);
		         	placeSearch.search(e.poi.name);
		         	self.searchKey = e.poi.name;
		         	/*console.log(autocomplete);
		         	console.log(e.poi.name);
		      	});
		    });*/
		},
		
		methods:{
			clickTask: function(task){
				console.log(task.id);
				this.$router.push({path:'/home/taskdetail',query:{task:task}});
			}
			/*clickSearch: function(){
				
				var key = this.searchKey;
				console.log("key---------------"+key);
				//将地址转化为经纬度
				AMap.service('AMap.Geocoder',function(){//回调函数
				    //实例化Geocoder
				    var geocoder = new AMap.Geocoder({
				        city: ""//城市，默认：“全国”
				    });
				    //TODO: 使用geocoder 对象完成相关功能
				    geocoder.getLocation(key, function(status, result) {
					    if (status === 'complete' && result.info === 'OK') {
					        //TODO:获得了有效经纬度，可以做一些展示工作
					        //比如在获得的经纬度上打上一个Marker
					        console.log(status);
					        console.log(result);
					        console.log("经度"+result.geocodes[0].location.lng);
					        console.log("纬度"+result.geocodes[0].location.lat);
					        //将经纬度发送到服务器，获取指定地点附近的任务


					    }else{
					        //获取经纬度失败
					        console.log(status);
					        console.log(result);
					        //有可能是获取不到经纬度，有可能不是POI，直接发送关键词到服务器，获取相关任务
					        

					    }
					}); 
				});
			}*/
		},
		components:{

		}
	}
</script>

<style type="text/css">
	.iconCss{
		width: 30px;
		height: 30px;
		vertical-align:middle;
	}
	.top{
		padding: 10px;
		background-color: #4F5D73;
		color: #FFFFFF;
		font-size: 14px;
	}
	.searchBtn{
	    width:25px; 
	    height:25px;
	    float: right;
	}

	.divMsgTLCss{
		font-size:12px; 
		margin-top: 10px;
		border-style: solid;
		border-width: 0px;
		border-bottom-width: 1px;
		border-top-width: 1px;
		border-color: #DCDCDC;
		background-color: #FFFFFF;
		color: #A9A9A9;
		padding-bottom: 5px;
		padding-top: 5px;
	}
	
	.amap-wrapper {
		margin-top: 5px;
		margin-left: 5px;
	  	width: 98%;
	  	height: 250px;
	}

	.ulCss{
		list-style: none;
		text-align: left;
		overflow-y: auto;
		overflow: auto; 
	}

	.liTaskListDivCss{
		text-align: left;
		border-width: 1px;
		/* margin-left: 20px; */
		padding: 0 20px;
		font-size: 13px;
		padding-top: 10px;
		padding-bottom: 5px;
		border-style: solid;
		border-bottom-width: 1px;
		border-top-width: 1px;
		border-left-width: 0;
		border-right-width: 0;
		border-color: #DCDCDC;
		background-color: #FFFFFF;
		color: #9E9E9E;
		margin-top: 6px;
	}
	.taskIconCss{
		width: 13px;
		vertical-align:middle;
		margin-left: 0px;
	}

	.labTaskListTtlCss{
		padding: 5px 2px 0 0;
		font-size: 14px;
		color: #4F5D73;
		font-weight: 550;
	}

	.progressContainer{  
      	height: 5px;  
      	width: 98%;  
      	border-radius: 3px;  
	    background-color: #DCDCDC;  
	    margin-left: 1%;  
	}  
	.progress{  
        background-color: #70BDA8;  
        border-radius: 3px;  
        height:5px;  
        line-height: 20px;  
	}  
	b{  
	    color:#fff;  
	    font-weight: 100;  
	    font-size: 12px;  
	    position:absolute;  
	    left:40%;   
	 }  

	 .inputSearch{
		width: 200px;
		padding: 5px 10px;
		border-radius: 6px;
		border-style: solid;
		border-width: 1px;
		border-color: grey;
		margin-right: 20px;
		color: grey;
		height: 17px;
		font-size: 14px;
		vertical-align: middle;
	}
	.btnSearch{
		vertical-align: middle;
		padding: 3px 18px;
		border-style: solid;
		border-width: 1px;
		border-radius: 5px;
		background-color: #ffffff;
		height: 27px;
		font-size: 14px;
		/* color: grey; */
	}
</style>