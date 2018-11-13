<template>
  <div class="login-wrapper">
    <div id="desc">
      <p>移动众包应用管理系统</p>
      <ul>
        <li><Icon type="checkmark-round"></Icon> 管理任务</li>
        <li><Icon type="checkmark-round"></Icon> 任务查看</li>
        <li><Icon type="checkmark-round"></Icon> 答案管理</li>
        <li><Icon type="checkmark-round"></Icon> 用户管理</li>
      </ul>
    </div>
    <transition name="form">
      <div class="form-wrapper" v-if="isShow">
        <Tabs value="login">
          <TabPane style="margin-top: 20px" label="登录" name="login">
            <Form ref="formCustom" :model="form" :rules="ruleCustom" :label-width="80">
              <FormItem label="用户名" prop="userName">
                <Input type="text" v-model="form.userName" />
              </FormItem>
              <FormItem label="密码" prop="password">
                <Input type="password" v-model="form.password" />
              </FormItem>
              <FormItem>
                  <Button :loading="loading" style="width: 40%" type="primary" @click="login('formCustom')">登录</Button>
                  <Button type="ghost" @click="reset('formCustom')" style="margin-left: 10%; width: 40%">重设</Button>
              </FormItem>
              <a href="javascript: void(0);" class="forgot-password">忘了密码？</a>
            </Form>
          </TabPane>
          <TabPane label="注册并登录" name="regist">
            <Form ref="registCustom" :model="regist" :rules="ruleCustom" :label-width="80">
              <FormItem label="用户名" prop="userName">
                <Input type="text" v-model="regist.userName" />
              </FormItem>
              <FormItem label="密码" prop="password">
                <Input type="password" v-model="regist.password" />
              </FormItem>
              <FormItem label="重复密码" prop="passwordAgain">
                <Input type="password" v-model="regist.passwordAgain" />
              </FormItem>
              <FormItem>
                  <Button :loading="loading" style="width: 40%" type="primary" @click="registRequest('registCustom')">登录</Button>
                  <Button type="ghost" @click="reset('registCustom')" style="margin-left: 10%; width: 40%">重设</Button>
              </FormItem>
            </Form>
          </TabPane>
        </Tabs>
    </div>
    </transition>
  </div>
</template>

<script>
// import types from '../store/types';
export default {
  name: 'Login',
  data() {
    const validateName = (rule, value, callback) => {
      if(value === '') {
        callback(new Error('请输入的账户名'))
      }else {
        callback();
      }
    }
    const validatePassword = (rule, value, callback) => {
      if(value === '') {
        callback(new Error('请输入密码'))
      }else {
        callback();
      }
    }
    const validatePasswordAgain = (rule, value, callback) => {
      if(value === '') {
        callback(new Error('请再次输入密码'));
      }else if(value !== this.regist.password) {
        callback(new Error('前后密码不一致'));
      }else {
        callback();
      }
    }
    return {
      form: {
        userName: '',
        password: ''
      },
      regist: {
        userName: '',
        password: '',
        passwordAgain: ''
      },
      ruleCustom: {
        userName: [{
          validator: validateName, trigger: 'blur'
        }],
        password: [{
          validator: validatePassword, trigger: 'blur'
        }],
        passwordAgain: [{
          validator: validatePasswordAgain, trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    login(ref) {
      this.$refs[ref].validate((valid) => {
        if(valid) {
          this.$store.dispatch('auth', { userName: this.form.userName, password: this.form.password, $router: this.$router}); // 远程登录
        }else {
          this.$Message.error('请再次登录!');
        }
      })
    },
    registRequest(ref) {
      this.$refs[ref].validate((valid) => {
        if(valid) {
          this.$store.dispatch('regist', { userName: this.regist.userName, password: this.regist.password, $router: this.$router })
        }else {
          this.$Message.error('请再次输入');
        }
      })
    },
    reset(ref) {
      this.$refs[ref].resetFields(); // 清空
    }
  },
  computed: {
    loading() {
      return this.$store.state.auth.loading
    },
    isShow() {
      return this.$store.state.auth.show
    },
    error() {
      return this.$store.state.auth.error;
    }
  }
}
</script>

<style scoped>
  .form-wrapper {
    position: relative;
    width: 30%;
    padding: 20px;
    border-radius: 10px;
    background-color: #fff;
    left: 50%;
    top: 160px;
    text-align: center;
    transition: opacity 1s;
  }
  .form-show {
    display: block;
    opacity: 1;
  }
  .login-wrapper {
    position: relative;
    width: 100%;
    height: calc(100% - 120px);
    background-color: rgb(115, 119, 122);
    border-top: 1px solid rgba(255, 255, 255, .2);
    border-bottom: 1px solid rgba(255, 255, 255, .2);
    
  }
  .title {
    font-size: 24px;
    margin-bottom: 20px;
  }
  .forgot-password {
    position: relative;
    left: 30%;
  }
  /* 定义缓动效果 */
  .form-enter-active, .form-leave-active {
    transition: all 1s;
  }
  .form-enter, .form-leave-to {
    opacity: 0;
    left: 60%;
  }
  #desc {
    position: absolute;
    top: 160px;
    left: 10%;
    color: #fff;
  }
  #desc > p {
    font-size: 24px;
  }
  #desc > ul > li {
    margin: 10px;
    list-style: none;
    font-size: 16px;
  }
</style>
