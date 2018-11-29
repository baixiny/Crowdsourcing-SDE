<template>
 
<div>
    <div class="top">
      <img src="../assets/icons/back.png" class="back" v-on:click="clickBack" >
      <label>接受任务</label>
      <label style="height:25px; width: 25px; background-color: #4F5D73 ; float: right"></label>
    </div>
    <!-- <div class="top">接受任务</div> -->
    <div style="padding-top: 45px"></div>
    <div class="box">
                <div class="imgWrap">
                 <img width="57" height="65" src="../assets/icons/任务.png" >
                  </div>
                  <div class="task_concent">
                  <h2>任务详情</h2>
                  <p>任务编号：{{task.id}}</p>
                  <p class="option">任务详情：{{task.description}}</p>
                  <p class="option">请回答以下问题并上传能够反映问题答案的照片</p>
                  </div>
              </div>
    <div class="title">  <h3>问题</h3>
 
        <div  v-for="item in quesArray">
 
           <div class="sentence"> {{item.description}}</div>
              
              <div class="labelStyle"  v-for="(labs,key,index) in item.label">
                 <!--  {{items.labledescription}} -->
                  
                    <div id="discount" class="labelStyle"> 
                     <label style="font-size: 12px"  v-if="item.qid=='q1'">
                       <input type="radio" v-model="picked[item.qid]" :value="labs.labledescription" />
                       {{labs.labledescription}}
                     </label>
                     <label style="font-size: 12px" v-if="item.qid=='q2'">
                       <input type="radio" v-model="picked[item.qid]" :value="labs.labledescription" />
                       {{labs.labledescription}}
                     </label>
                     <label style="font-size: 12px" v-if="item.qid=='q3'">
                       <input type="radio" v-model="picked[item.qid]" :value="labs.labledescription" />
                       {{labs.labledescription}}
                     </label>
                    </div>
              </div>
<!-- 丰富问题提交形式 -->
              <div class="sentence"> 
              <p>已选{{picked[item.qid]}}</p> 
               <br>
            </div>
        </div>
 
    </div>
    
    <div class="image-list">
     <h3>拍照</h3>
        <div style="text-align:center" ref="divGenres" class="list-default-img" v-show="isPhoto" @click.stop="addPic">
          <img src="../assets/icons/添加图片.png" class="iconCss" />
          <br>
          <span>请选择或拍照上传照片</span>
          
           <input  type="file" id="fileinput" name="file" ref="fileInput" accept="image/jpg,image/png,image/jpeg,image/gif" capture="camera" @change="uploading($event)" style="display: none;" >
        
        </div>
        <ul class="list-ul" v-show="!isPhoto">
          <li class="list-li " v-for="(iu, index) in imgUrls">
            <a class="list-link" @click='previewImage(iu)'>
              <img :src="iu">
            </a>
            <span class="list-img-close" @click="delImage(index)"></span>
          </li>
 
          <li class="list-li">
            <span class="add-img" @click="addPic"> </span>
          </li>
        </ul>
        
    </div>
    
    <button class="btnSubmitSetCss" @click = 'clickSubmit'>提交</button>
    <!-- <div>
    <button class="btn1"  @click="submit($event)">上传图片</button>
    </div> -->
 
    <!-- <div class="bottom">
       <button class="btn" v-on:click="clickSubmit">提交</button>
        <button class="btn" v-on:click="clickBack">返回</button>
    </div> -->
 
</div>
 
</template>
 
<script>
  
import global from '../Global'
import lrz from "lrz"
 
  export default {
    data: function () {
      return {
        task:'',
        show: false,
        imgUrls: [],
        urlArr: [],
        isPhoto: true,
        btnTitle: '',
        isModify: false,
        previewImg:'',
        isPreview: false,
        discount:'',
        taskInfo:'',
        quesArray:[],
        labelArray:[],
        picked:[],
        username:'',
        fileArray: []
 
      }
    },
    watch: {
      imgUrls: 'toggleAddPic'
    },
 
    mounted(){
      console.log('---------accepttask mounted--------'); 
      this.task = this.$route.query.task;
      console.log(this.task); 
      this.username=localStorage.getItem('username');
    /*  this.taskInfo=this.task;*/
      this.quesArray=this.task.qusetion; 
     
      console.log(this.quesArray);
    /*var self = this;
        this.$ajax({
              method: 'PUT',
              url: 'http://211.87.239.94:8080/Spring4MVCCRUDRestService/task/accepttask',
              data: {
                tid: this.task.id
              }
          })
        .then(function (response) {
            console.log(response.data.state);
            if(response.data.state == 1){
              self.taskInfo=response.data.message;
              self.quesArray=response.data.message.question; 
              console.log(response.data.message.question);        
              
            }else if(response.data.state == 2){
              self.taskInfo = '该任务无详情';
            }           
        })
        .catch(function (response) {
            console.log(response);
            self.taskInfo = '网络连接失败:(';
        })*/
    },
 
    methods: {
      
      clickBack: function(){
        this.$router.push({path:'/home',query:{page:1}});
      },
    
      clickSubmit:function(e){
 
         var question = [];
         var tid=this.task.id;
         console.log(this.task.id)
 
         console.log(this.picked)
         for (var i=0; i<this.quesArray.length;i++){
            var qid= this.quesArray[i].qid;
            var description=this.quesArray[i].description;
            var labelid=this.quesArray[i].lableid;
            var labeldescription = this.picked[qid];
            question.push({"qid":qid,"description":description,"labelid":labelid,"labeldescription":labeldescription});
 
         }
        console.log(question);
        var location = sessionStorage.getItem('location');
        var longitude = sessionStorage.getItem('longitude');
        var latitude = sessionStorage.getItem('latitude');
        console.log(location);
        console.log(longitude);
        console.log(latitude);
        var self=this;
        var formdata = new FormData();
        /*formdata.append('file',self.fileArray);*/

              
        formdata.append('username',self.username);
        formdata.append('tid',self.task.id);
 
        for(var i=0; i<self.fileArray.length; i++){
              formdata.append('file',self.fileArray[i]);
          }
        console.log(self.fileArray);
        if(self.fileArray!=0){        
            this.$ajax({
              method: 'POST',
              url: global.urlUploadAnswer,
              //规范url
              data: {
                  username:self.username,
                  tid: self.task.id,
                  question:question,
                  location: location,
                  longitude: longitude,
                  latitude: latitude
              }
          })
          .then(function (response) {
            console.log(response.data.state);
            if(response.data.state == 1){
              
              console.log(response.data.state);
              /*alert("上传成功");*/  
              /*self.$router.push({path:'/home',query:{page:1}});*/ 
            event.preventDefault();//取消默认行为  

              let config = {
                  headers: {
                  'Content-Type': 'multipart/form-data'  //之前说的以表单传数据的格式来传递fromdata
                  }
              };
              self.$ajax.post(global.urlUploadPic,formdata,config)
              .then(response=>{
                  console.log(response.data);
                  if(response.data==1){
                    alert("答案和所附图片提交成功");
                    self.$router.push({path:'/home',query:{page:1}});
                  }
                    else{ 
                      alert("未附图片的答案提交成功");
                      self.$router.push({path:'/home',query:{page:1}});
                  }
                })
 
              .catch(function (response) {
              console.log(response);
              alert("未附图片的答案提交成功");
              self.$router.push({path:'/home',query:{page:1}});
              })
               
              
            }
            else if(response.data.state == 2){
             alert("任务超时，上传失败");  
            }           
              
          }.bind(this))
          .catch(function (response) {
            console.log(response);
            self.taskInfo = '网络连接失败:(';
          })
        }
        else{
          alert("请拍照或选择需要上传的图片");
        }
        
      },
 
    toggleAddPic: function() {
        let vm = this;
        if(vm.imgUrls.length >= 1) {
          vm.isPhoto = false;
        } else {
          vm.isPhoto = true;
        }
      },
    uploading(event){
 
        this.file = event.target.files[0];//获取文件
        var windowURL = window.URL || window.webkitURL;
        this.file = event.target.files[0];
        //创建图片文件的url
        this.src = windowURL.createObjectURL(event.target.files[0]);

        var files = event.target.files || event.dataTransfer.files;
        if(!files.length) 
          return;
        this.createImage(files, event);
        this.fileArray.push(this.file);
        console.log(this.fileArray);
    },
    /*submit(){
        event.preventDefault();//取消默认行为
        var tid=this.task.id;
      
        var formdata = new FormData();
        for(var i=0; i<this.fileArray.length; i++){
            formdata.append('file',this.fileArray[i]);
        }
        console.log(this.fileArray);
        
        var self=this;
        formdata.append('username',self.username);
        formdata.append('tid',self.task.id);

        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'  //之前说的以表单传数据的格式来传递fromdata
            }
        };
        this.$ajax.post(global.urlUploadPic,formdata,config)
            .then(response=>{
                console.log(response.data);
                if(response.data==1){
                    alert("图片上传成功，请确认已选择问题答案，并点击【提交】完成任务");
                }
                else{ 
                    alert("请选择或拍照上传图片")
                }
        })
        .catch(function (response) {
            console.log(response);
            alert("请选择或拍照上传图片");
        })
          
          
             
    },*/
      addPic: function(e) {
          /*let vm = this;
          $('input[type=file]').trigger('click');
          return false;*/
          let els = this.$refs.divGenres.querySelectorAll('input[type=file]')
          els[0].click()
          return false
      },
      onFileChange: function(e) {
        var files = e.target.files || e.dataTransfer.files;
        if(!files.length) return;
        this.createImage(files, e);
      },
      createImage: function(file, e) {
        let vm = this;
        lrz(file[0], { width: 480 }).then(function(rst) {
          vm.imgUrls.push(rst.base64);
          return rst;
        }).always(function() {
                // 清空文件上传控件的值
                e.target.value = null;
                });
      },
 
      delImage: function(index) {
        let vm=this;
          
        if (index == 0) {
        vm.imgUrls.splice(index, 1);
        
        /*let inputID=''; 
            //需要清除value的input
        if(e.target.nodeName=='IMG'){
          e.target.src='';
          inputID=e.target.id.replace('img_','');    //获得需要清除value的input
          $('input#'+inputID).val("");
        }*/
      }
         if (index == 1) {
        vm.imgUrls.splice(index, 1);
        }
        if (index == 2) {
        vm.imgUrls.splice(index, 1);
        }
 },
    
      previewImage: function(url){
        let vm = this;
        vm.isPreview = true;
        vm.previewImg = url;
      },
      closePreview: function(){
        let vm = this;
        vm.isPreview = false;
        vm.previewImg = "";
 
      },
   
}
}
 
</script>
 
<style>
 
 
.labelStyle{
  display:inline;
  padding: 0px 5px 0px;
  margin: 0 10px 0;
  },
 
 
.option {
      margin: 10px 0 0 0;
    height: 10px;
    line-height: 5px;
    font-size: 12px;
    color: #333;
  }
 
.imgWrap {
   
    padding: 0.1rem 0;
  }
 
.box {
  background-color: white;
  padding: 5px 20px 5px;
  display: flex;
  border-style: solid;
  border-left-width: 0px;
  border-right-width: 0px;
  border-top-width: 0px;
  border-bottom-width: 5px;
  border-color:   #F0F0F0;
  
  }
  .title{
    font-size:16px;
    text-align: left;
    padding: 0px 20px 0px;
    border-style: solid;
  border-left-width: 0px;
  border-right-width: 0px;
  border-top-width: 0px;
  border-bottom-width: 5px;
  border-color:   #F0F0F0;  
  }
  .sentence{
    font-size:12px;
    text-align: left;
    padding: 0px 30px 0px;  
  }
 
.task_concent{
      background-color: white;
      margin: 0 20px 0;
      padding: 0.1rem 0;
      float: left;
      text-align: left;       
  }
  
.bottom{
    width: 100%;
    position: fixed;
    bottom: 0;
    height: 50px;
    font-size: 16px;
    
  }
  .unclicked{
    width: 100px;
    height: 50px;
    background-color: #4F5D73;
    border:none;
    color: grey;
  }
  .btn{
    width: 100px;
    height: 50px;
    background-color: #4F5D73;
    border:none;
    color: #FFFFFF;
  }
 
 
 
.top{
    /* display: flex; */
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
.iconCss{
    width: 50px;
    height: 50px;
    vertical-align:middle;
  }
.image-list{
 font-size:16px;
    text-align: left;
    padding: 0px 20px 0px;
}
.list-ul {
    list-style: none;
    /* 去掉ul前面的符号 */
    margin: 0px;
    /* 与外界元素的距离为0 */
    padding: 0px;
    /* 与内部元素的距离为0 */
    width: auto;
    /* 宽度根据元素内容调整 */
  }
 
  /* 所有class为menu的div中的ul中的li样式 */
 
  .list-li {
    float: left;
    /* 向左漂移，将竖排变为横排 */
    padding: 8px;
    /* 与内部元素的距离为0 */
    display: inline-block;
    position: relative;
    text-align: center;
  }
 
  .list-link img {
    width: 100px;
    height: 100px;
  }
 
  /* 所有class为menu的div中的ul中的a样式(包括尚未点击的和点击过的样式) */
 
  .list-link a:visited {
    background-color: #465c71;
    /* 背景色 */
    border: 1px #4e667d solid;
    /* 边框 */
    color: #dde4ec;
    /* 文字颜色 */
    display: flex;
    /* 此元素将显示为块级元素，此元素前后会带有换行符 */
    line-height: 1.35em;
    /* 行高 */
    padding: 4px 20px;
    /* 内部填充的距离 */
    text-decoration: none;
    /* 不显示超链接下划线 */
    white-space: nowrap;
    /* 对于文本内的空白处，不会换行，文本会在在同一行上继续，直到遇到 <br> 标签为止。 */
    overflow: hidden;
  }
 
  /* 所有class为menu的div中的ul中的a样式(鼠标移动到元素中的样式) */
 
  .list-link a:hover {
    background-color: #bfcbd6;
    /* 背景色 */
    color: #465c71;
    /* 文字颜色 */
    text-decoration: none;
    /* 不显示超链接下划线 */
  }
 
  /* 所有class为menu的div中的ul中的a样式(鼠标点击元素时的样式) */
 
  .list-link a:active {
    background-color: #465c71;
    /* 背景色 */
    color: #cfdbe6;
    /* 文字颜色 */
    text-decoration: none;
    /* 不显示超链接下划线 */
  }
 
  .list-img-close {
    background: #ffffff url('../assets/icons/删除.png') no-repeat right top;
    border-color: #ff4a00;
    background-position: center;
    background-size: 35px 35px;
    display: block;
    float: left;
    width: 10px;
    height: 10px;
    position: absolute;
    top: 5%;
    left: 71%;
    margin-top: 0px;
    margin-left: 0px;
    padding: 8px;
    z-index: 10;
    border-radius: 5px;
    text-align: center;
  }
 
  .add-img {
    display: block;
    background-image: url('../assets/icons/添加图片.png');
    background-repeat: no-repeat;
    width: 100px;
    height: 100px;
    background-position: center;
    background-size: 100px 100px;
  }
 
  .btn1{
    margin-top: 20px;
    width: 110px;
    height: 35px;
    font-size: 16px;
    border-style: solid;
    border-radius: 10px;
    border-width: 1px;
    border-color: #4F5D73;
    padding: 3px 20px;
    background-color: #FFFFFF;
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
  
  
</style>