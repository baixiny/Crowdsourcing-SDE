<template>
<div  style="overflow: auto;">
    <div class="topQn">
      	填写问卷
    </div>
    <div style="width: 90%; margin: 20px auto;">
		<div class="divDescCss">主题：{{ask.description}}</div>
		<div class="divContentCss">
			<!-- <div style="font-size:13px; color: #9E9E9E; text-align: left">
				请回答下面几个问题：
			</div> -->
			<ul class="ulAskCss">
				<li v-for="ques in qlist">
					<div class="divTitleCss">{{ques.qid}}：&nbsp;{{ques.qdesc}}</div>
					<ul>
						<li v-for="label in ques.labels">
							<div 
								class="divChoiceCss" 
								v-bind:class="{ divClickChoiceCss: label.isChoose }"
							 	@click="clickChoise(ques.qid,label.lid)">{{label.ldesc}}</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<div style="font-size:13px; color: #9E9E9E; text-align: left; margin-top: 10px">
			截止日期：{{ask.deadline}}
		</div>
    </div>
    <button class="btnSubmitCss" @click = 'clickSubmit'>提交</button>
    <button class="btnSubmitCss" @click = 'clickPass'>跳过</button>
</div>
</template>

<script type="text/javascript">
	import global from '../Global'
	export default {
		name:'QuestionnaireWithTask',
	    data(){
	    	return{
	    		task:'',
	    		ask:'',
	    		choices:[],
	    		qlist:[]
	    	}
	    },
	    mounted(){
	    	console.log('------------------与任务相关的问卷--------------');
	    	this.ask = this.$route.query.ask;
	    	this.task = this.$route.query.task;

	    	var qs = this.ask.content;
	    	for(var i=0; i<qs.length; i++){
	    		var qid = qs[i].qid;
	    		var qdesc = qs[i].description;
	    		var labels = [];
	    		for(var j=0; j<qs[i].label.length; j++){
	    			var label = qs[i].label[j];
	    			var lid = label.labelid;
	    			var ldesc = label.labeldescription;
	    			labels.push({"lid": lid,"ldesc": ldesc,"isChoose":false});
	    		}
	    		this.qlist.push({
	    			'qid':qid,
	    			'qdesc':qdesc,
	    			'labels':labels
	    		});
	    	}
	    	console.log(this.qlist);
	    },
	    methods:{
	    	clickSubmit: function(){
	    		var username = localStorage.getItem("username");
	    		var qnaireid = this.ask.qnaireid;
	    		var answer = []; //存储答案
	    		for(var i=0; i<this.qlist.length; i++){
	    			var qid = this.qlist[i].qid;
	    			var description = this.qlist[i].qdesc;
	    			var ls = this.qlist[i].labels;
	    			for(var j=0; j<ls.length; j++){
	    				if(ls[j].isChoose == true){
	    					var labelid = ls[j].lid;
	    					var labeldescription = ls[j].ldesc;
	    					answer.push({"qid":qid, "description":description, 
	    						"labelid":labelid, "labeldescription":labeldescription});
	    				}
	    			}
	    		}

	    		console.log(answer);
	    		var self = this;
	    		this.$ajax({
			      	method: 'POST',
			      	url: global.urlUploadQuesnaire,
			      	data: {
				        username: username,
				        qnaireid: qnaireid,
				        longitude: 0,
				        latitude: 0,
				        location: 0,
				        answer: answer
			      	}
			   	})
				.then(function (response) {
				    console.log(response.data);
				   	if(response.data.state == 0){
				   		alert('问卷提交成功 :)');
				   		self.$router.push({path:'/home/accepttask',query:{task:self.task}});
				   	}
				})
				.catch(function (response) {
				    console.log(response);
				    self.msg = '网络连接失败:(';
				});

	    		/*this.$router.push({path:'/home',query:{page:1}});*/
	    	},
	    	clickChoise: function(qid,labelid){
	    		/*console.log(qid,labelid);*/
	    		for(var i=0; i<this.qlist.length; i++){
	    			if(qid == this.qlist[i].qid){
	    				for(var j=0; j<this.qlist[i].labels.length; j++){
	    						this.qlist[i].labels[j].isChoose = false;
	    				}
	    				for(var j=0; j<this.qlist[i].labels.length; j++){
	    					if(labelid == this.qlist[i].labels[j].lid){
	    						console.log(this.qlist[i].labels[j].isChoose);
	    						this.qlist[i].labels[j].isChoose = true;
	    						console.log(this.qlist[i].labels[j].isChoose);
	    					}
	    				}
	    			}
	    		}
	    	} ,
	    	clickPass: function(){
	    		this.$router.push({path:'/home/accepttask',query:{task:this.task}});
	    	}
	    }
	}
</script>

<style type="text/css">
	.topQn{
	    text-align:center;
	    padding: 10px;
	    background-color: #4F5D73;
	    color: #FFFFFF;
	    font-size: 14px;
  	}

  	.back{
	    width:25px; 
	    height:25px;
	    float: left;
  	}

	.divDescCss{
		font-size: 15px;
		font-weight: 600;
		text-align: left;
	}

	.divContentCss{
		border-top-width: 2px;
		border-bottom-width: 0;
		border-left-width: 0;
		border-right-width: 0;
		border-color: #4f5d73;
		border-style: solid;
		min-height: 100px;
		margin-top: 10px;
		max-height: 400px;
		padding-top: 5px;
	}

	.ulAskCss{
		list-style: none;
		text-align: left;
		overflow-y: auto;
	}

  	.btnSubmitCss {
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
		width: 30%;
		height: 35px;
		margin: 30px 10px;
	}

	.divTitleCss{
		font-size:14px;
		color:#666666;
		font-weight: 600;
		margin-top: 5px;
		margin-bottom: 3px;
	}

	.divChoiceCss {
		-moz-box-shadow:inset 0px 1px 0px 0px #ffffff;
		-webkit-box-shadow:inset 0px 1px 0px 0px #ffffff;
		box-shadow:inset 0px 1px 0px 0px #ffffff;
		background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ffffff), color-stop(1, #f6f6f6));
		background:-moz-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
		background:-webkit-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
		background:-o-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
		background:-ms-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
		background:linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#f6f6f6',GradientType=0);
		background-color:#ffffff;
		-moz-border-radius:6px;
		-webkit-border-radius:6px;
		border-radius:3px;
		border:1px solid #dcdcdc;
		display:inline-block;
		cursor:pointer;
		color:#666666;
		font-family:Arial;
		font-size:14px;
		padding:3px 0;
		text-decoration:none;
		width: 98%;
		margin: auto;
		text-align: center;
		margin-bottom: 5px;
		text-shadow:0px 1px 0px #ffffff;
	}
	.divChoiceCss:hover {
		background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f6f6f6), color-stop(1, #ffffff));
		background:-moz-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
		background:-webkit-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
		background:-o-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
		background:-ms-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
		background:linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f6f6f6', endColorstr='#ffffff',GradientType=0);
		background-color:#f6f6f6;
	}
	.divChoiceCss:active {
		position:relative;
		top:1px;
	}
 
	.divClickChoiceCss {
		-moz-box-shadow:inset 0px 1px 0px 0px #276873;
		-webkit-box-shadow:inset 0px 1px 0px 0px #276873;
		box-shadow:inset 0px 1px 0px 0px #276873;
		background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #599bb3), color-stop(1, #408c99));
		background:-moz-linear-gradient(top, #599bb3 5%, #408c99 100%);
		background:-webkit-linear-gradient(top, #599bb3 5%, #408c99 100%);
		background:-o-linear-gradient(top, #599bb3 5%, #408c99 100%);
		background:-ms-linear-gradient(top, #599bb3 5%, #408c99 100%);
		background:linear-gradient(to bottom, #599bb3 5%, #408c99 100%);
		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#599bb3', endColorstr='#408c99',GradientType=0);
		background-color:#599bb3;
		-moz-border-radius:6px;
		-webkit-border-radius:6px;
		border-radius:6px;
		border:1px solid #29668f;
		display:inline-block;
		cursor:pointer;
		color:#ffffff;
		font-family:Arial;
		font-size:14px;
		width: 98%;
		margin: auto;
		text-align: center;
		padding:3px 0;
		margin-bottom: 5px;
		text-decoration:none;
		text-shadow:0px 1px 0px #3d768a;
	}
	.divClickChoiceCss:hover {
		background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #408c99), color-stop(1, #599bb3));
		background:-moz-linear-gradient(top, #408c99 5%, #599bb3 100%);
		background:-webkit-linear-gradient(top, #408c99 5%, #599bb3 100%);
		background:-o-linear-gradient(top, #408c99 5%, #599bb3 100%);
		background:-ms-linear-gradient(top, #408c99 5%, #599bb3 100%);
		background:linear-gradient(to bottom, #408c99 5%, #599bb3 100%);
		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#408c99', endColorstr='#599bb3',GradientType=0);
		background-color:#408c99;
	}
	.divClickChoiceCss:active {
		position:relative;
		top:1px;
	}

</style>