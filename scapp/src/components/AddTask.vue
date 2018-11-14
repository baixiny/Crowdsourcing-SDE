
<template>
  <div style="overflow: auto;">
  <div class="topAddTask">
      <label>发布任务</label>
    </div>
  <div class="task-wrapper">
    <Form ref="task" :model="taskData" :rules="ruleValidate" :label-width="80">
      <FormItem label="任务名称" prop="taskName">
        <Input type="text" v-model="taskData.taskName" />
      </FormItem>
      <FormItem label="任务描述" prop="taskDesc" >
        <Input type="textarea" v-model="taskData.taskDesc" />
      </FormItem>
      <FormItem label="地址" prop="taskAddr">
        <AutoComplete
          v-model="taskData.taskAddr"
          :data="dataComplete"
          @on-search="showAddresses"
          placeholder="输入地址，不填代表任务与位置无关"
        >
        <Option v-for="item in dataComplete" :value="JSON.stringify({name: item.name, location: item.location})" :key="item.id">
          <p>地名：{{item.name}}</p>
          <p>地址：{{item.address}}</p>
        </Option>
        </AutoComplete>
      </FormItem>
      <FormItem label="时限/小时" prop="taskTime">
        <InputNumber :min="1" v-model="taskData.taskTime"></InputNumber>
      </FormItem>
      <FormItem label="回答人数" prop="answersNum">
        <InputNumber :min="1" v-model="taskData.answersNum"></InputNumber>
      </FormItem>
      <FormItem label="奖励金" prop="taskPrice">
        <Input type="text" v-model="taskData.taskPrice" />
      </FormItem>
      <FormItem
        v-for="(item, index) in taskData.questions"
        v-if="item.status"
        :key="index"
        :label="'问题'+item.index"
        :prop="'questions.'+index+'.value'"
        :rules="{required: true, message: '问题不可以为空', trigger: 'blur'}"
      >
        <Row>
          <Col span="18">
            <Input type="text" v-model="item.value"/>
          </Col>
          <Col span="4" offset="1">
            <Button type="ghost" @click="handleRemove(index)">删除</Button>
          </Col>
        </Row>
        <Row>
            <RadioGroup>
              <Radio 
                v-for="(item1, index1) in item.options" 
                :key="index1"
                :label="item1"
              >
                  选项{{index1 + 1}}: {{ item1 }}
                  <Button type="ghost" size="small" @click="removeOption(index, index1)">删除</Button>
              </Radio>
            </RadioGroup>
        </Row>
        <FormItem>
        <Button type="dashed" @click="addOptions(index)" icon="plus-round">添加选项</Button>
        </FormItem>
      </FormItem>
      <FormItem>
        <Button type="dashed" long @click="addQuestions" icon="plus-round">添加问题</Button>
      </FormItem>
      <FormItem>
        <Button type="dashed" long @click="addQuestions" icon="plus-round">添加关联问卷</Button>
      </FormItem>
      <FormItem>
        <Row>
          <Col span="10">
            <Button :loading="loading" type="primary" @click="handleSubmit('task')" long>发布任务</Button>
          </Col>
          <Col span="10" offset="2">
            <Button type="ghost" @click="handleReset('task')" long>重新填写</Button>
          </Col>
        </Row>
      </FormItem>
    </Form>
    <div v-if="error">
      <Alert type="error" show-icon closable>
        发生错误了~
        <span slot="desc">{{error}}</span>
      </Alert>
    </div>
  </div>
</div>
</template>

<script>
import jsonp from 'jsonp';
import global from '../Global'
import { mapState } from 'vuex';

//import { DatetimePicker } from 'mint-ui';

//Vue.component(DatetimePicker.name, DatetimePicker);
export default {
  name: 'AddTask',
  data(){
    return {
      index: 1,
      dataComplete: [],
      tempValue: '',
      taskData: {
        taskName: '',
        taskDesc: '',
        taskAddr: '',
        taskTime: 1,
        answersNum:10,
        taskPrice: '',
        questions: [
          {
            value: '',
            index: 1,
            status: 1,
            options: [],
          }
        ]
      },
      ruleValidate: {
        taskName: [
          {
            required: true, message: '必须输入任务名称'
          }
        ],
        taskDesc: [
          {
            required: true, message: '任务描述应不少于一个字符'
          }
        ],
        taskAddr: [
          {
            required: true, message: '应为任务选择一个地址'
          }
        ],
        taskTime: [
          {
            required: true, message: '请输入任务时间，默认值为1小时'
          }
        ],
        answersNum: [
          {
            required: true, message: '请输入期待回答问题的人数，默认值为10人'
          }
        ],
        taskPrice: [
          {
            required: true, message: '必须输入报酬(0表示无报酬)'
          }
        ]
      }
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
    handleRemove(index) {
      this.taskData.questions.splice(index, 1);
      //this.index --; // 删除对应index的问题
    },
    removeOption(index, index1) {
      this.taskData.questions[index].options.splice(index, 1);
      this.index --; // 
    },
    addQuestions() {
      this.index ++;
      this.taskData.questions.push({
        value: '',
        index: this.index,
        status: 1,
        options: []
      })
    },
    addOptions(index){
      this.$Modal.confirm({
        render: (h) => {
          return h('Input', {
            props: {
              autofocus: true,
              placeholder: '输入选项'
            },
            on: {
              input: (val) => {
                this.tempValue = val;
              }
            }
          }
          );
        },
        onOk: () => {
          this.taskData.questions[index].options.push(this.tempValue);
        }
      })
    },
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if(valid) {
          // 验证成功，向后端发起请求


          this.$ajax({
              method: 'POST',
              url: global.urlAddTask,
              data: {
                data: this.taskData,
              }
          })
        .then(function (response) {
              console.log(response.data.state);
              alert("发布任务成功");
              this.$router.push({path:'/home',query:{page:1}});
        }.bind(this))
        .catch(function (response) {
            console.log(response);
            self.msg = '网络连接失败:(';
        });




          // this.$store.dispatch('addTask', { data: this.taskData }); 
          // dispatch一个action
        }else {
          // 验证成功的一些提示
        }
      })
    },
    handleReset(name) {
      this.$refs[name].resetFields(); // 清空所有组件
    }
  },
 /* computed: {
    ...mapState({
      loading: state => state.task.updateing,
      error: state => state.task.error
    })
  }*/
  // mounted(){
  //   // 组件加载完成即获得当前所在的坐标
  //   // 在chrome中测试不成功，据说是被墙了。
  //   if(navigator.geolocation) {
  //     const that = this;
  //     navigator.geolocation.getCurrentPosition(function(pos) {
  //       that.location = {
  //         ...that.location,
  //         latitude:pos.coords.latitude,
  //         longitude: pos.coords.longitude
  //       }
  //       console.log(pos.coords);
  //     },
  //     function(error) {
  //         let result;
  //         switch(error.code) 
  //         {
  //         case error.PERMISSION_DENIED:
  //           result="用户不允许使用地址，将不能定位。"
  //           break;
  //         case error.POSITION_UNAVAILABLE:
  //           result="地址信息不可用。"
  //           break;
  //         case error.TIMEOUT:
  //           result="请求时间过长，请检查网络后重试。"
  //           break;
  //         case error.UNKNOWN_ERROR:
  //           result="未知错误，请重试。"
  //           break;
  //         }
  //         alert(result);
  //       })
  //   }
  // },
  // updated() {
  //   console.log(this.taskData.taskAddr);
  // }
}
</script>

<style scoped>
  .task-wrapper {
    position: relative;
    width: 90%;
    top: 10px;
  }
  .topAddTask{
    /* display: flex; */
    text-align:center;
    padding: 10px;
    background-color: #4F5D73;
    color: #FFFFFF;
    font-size: 14px;
  }
</style>
