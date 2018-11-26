<template>
  <div style="overflow: auto;">	
  	<div class="top">
      <img src="../assets/icons/back.png" class="back" v-on:click="clickBack" >
      <label>更改位置</label>
      <label style="height:25px; width: 25px; background-color: #4F5D73 ; float: right"></label>
    </div>
    <div style="padding-top: 45px"></div> 
    <div>
    	<AutoComplete
    	v-model="address"
        :data="dataComplete"
        @on-search="showAddresses"
        placeholder="输入地址,系统将自动获取目标地址的经纬度值"
        	>
        	<Option v-for="item in dataComplete" :value="item.name+','+item.location" :key="item.id">
          		<p>地名：{{item.name}}</p>
          		<p>地址：{{item.address}}</p>
        	</Option>
        </AutoComplete>
    </div>
    <button class="btnAddressBottom" v-on:click="clickAddress">确认</button>
  </div>

</template>
<script>
import jsonp from 'jsonp';
import global from '../Global'
import { mapState } from 'vuex';
export default {
  name: 'GetPos',
  data(){
    return {
    	address:'',
    	dataComplete: [],
    	}
    },
  methods: {	
	showAddresses(value) {
      // 得到当前所在的城市
      // 取得模糊查询的结果赋值给dataComplete
      // const { latitude, longitude } = this.location;
      // 这里不再使用location
      	jsonp(`http://restapi.amap.com/v3/assistant/inputtips?keywords=${value}&key=27a945c10c641d38a4fb25473c91069b`, null, (err, data) =>{
        if(err) {
          console.log(err.message);
        }else {
          this.dataComplete = data.tips;
        }
      })
    },
    clickBack: function(){
        this.$router.push({path:'/home',query:{page:1}});
    },
    clickAddress:function(){
    	var addr = new Array();
    	addr = this.address.split(",");
    	sessionStorage['auto'] = 0;
    	console.log(sessionStorage.getItem('auto'));
    	sessionStorage.setItem('longitude',addr[1]);
    	sessionStorage.setItem('latitude',addr[2]);
    	this.$router.push({path:'/home',query:{page:1}});
    }
  }
}
</script>
<style type="text/css">
	.top{
		padding: 10px;
		background-color: #4F5D73;
		font-size: 16px;
		color: #FFFFFF;
		top: 0;
		height: 40px;
		width: 100%;
		position: fixed;
		z-index: 100;
	}
	.back{
		width:25px; 
		height:25px;
		float: left;
	}
	.btnAddressBottom {
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
	.btnAddressBottom:hover {
		background-color:#e7eaf0;
	}
	.btnAddressBottom:active {
		position:relative;
		top:1px;
	}

</style>