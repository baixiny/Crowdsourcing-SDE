<template>
  <div class="user-wrapper">
    <Table width="100%" stripe border :columns="columns" :data="userData"></Table>
    <Alert style="margin-top: 10px" type="error" v-if="error" closable>{{error}}</Alert>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'UserList',
  data() {
    return {
      columns: [
        {
          title: '用户编号',
          key: 'u_id'
        },
        {
          title: '用户姓名',
          key: 'u_name'
        },
        {
          title: '联系电话',
          key: 'phone_number'
        },
        {
          title: '报酬',
          key: 'price'
        }
      ],
      users: [
        {
          u_id: '0001',
          u_name: '张三',
          phone_number: '13125741996',
          price: '1290'
        },
        {
          u_id: '0002',
          u_name: '李四',
          phone_number: '13125741996',
          price: '540'
        },
        {
          u_id: '0003',
          u_name: '刘看山',
          phone_number: '13125741996',
          price: '1410'
        },
        {
          u_id: '0004',
          u_name: '袁伟伦',
          phone_number: '13125741996',
          price: '234'
        }
      ]
    }
  },
  computed: {
    ...mapState({
      loading: (state) => state.user.loading,
      userData: (state) => state.user.data,
      error: (state) => state.user.error
    })
  },
  mounted() {
    this.$store.dispatch('loadUserInfo'); // 加载用户信息
  }
}
</script>

<style scoped>
.user-wrapper {
  position: relative;
  width: 90%;
  left: 5%;
  top: 20px;
}
</style>

