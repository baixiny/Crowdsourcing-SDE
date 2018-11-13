<template>
  <div class="header-wrapper">
    <p class="title">移动众包平台管理员界面</p>
    <div v-if="userName" class="user-wrapper">
      <!--<Avatar icon="person" src="../../static/img.jpg"></Avatar> //放头像照片 -->  
      <Dropdown style="margin-left: 10px" trigger="click">
        <!-- <a class='user-name' href="javascript: void(0);">
          {{userName}}  //用户名
          <Icon type="arrow-down-b"></Icon>
        </a> -->
        <DropdownMenu slot="list">
         <!--  <DropdownItem><a>我的信息</a></DropdownItem> -->
          <DropdownItem><a href="javascript: void(0);" @click="logout">登出</a></DropdownItem> 
        </DropdownMenu>        
      </Dropdown>  
    </div>
    <div v-if="!userName" class="user-wrapper"> 
      <p class="user-name">欢迎你！</p>
      <Button type="primary" @click="showLoginForm">请登录/注册</Button>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import types from '../store/types';
export default {
  name: 'Header',
  methods: {
    ...mapMutations({
      showLoginForm: types.SHOW_LOGIN_FORM
    }),
    logout(){
      this.$store.commit(types.CLEAR_TOKEN_AND_LOGOUT, { $router: this.$router });
    }
  },
  mounted() {
    if(this.userName) {
      this.$router.push('/dashbord');
    }
  },
  computed: {
    userName() {
      return this.$store.state.auth.userName || localStorage.getItem('userName');
    }
  }
}  
</script>

<style scoped>
  .header-wrapper {
    position: relative;
    width: 100%;
    height: 60px;
    top: 0;
    background-color: rgb(115, 119, 122);
    color: #fff;
  }
  .title {
    height: 60px;
    line-height: 60px;
    font-size: 24px;
    margin-left: 10px;
  }
  .user-wrapper {
    display: flex;
    align-items: center;
    width: 15%;
    height: 60px;
    position: relative;
    left: 85%;
    top: -60px;
  }
  .user-name {
    font-size: 18px;
    color: #fff;
  }
</style>
