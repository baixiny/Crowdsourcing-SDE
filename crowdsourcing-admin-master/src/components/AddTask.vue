<template>
  <div class="task-wrapper">
    <Form ref="task" :model="taskData" :rules="ruleValidate" :label-width="80">
      <FormItem label="tasktype" prop="taskName">
        <Input type="text" v-model="taskData.taskName" />
      </FormItem>
      <FormItem label="task discription" prop="taskDesc">
        <Input type="textarea" v-model="taskData.taskDesc" />
      </FormItem>
     <!--  <FormItem label="任务关联问卷编号">
        <Button  @click="inputvisible=true" >
        </Button>
      </FormItem>
      <FormItem 
        v-if="inputvisible">
        <input type="text" v-model="taskData.questionnaireId"/>
      </FormItem> -->
      <FormItem label="location" prop="taskAddr">
        <AutoComplete
          v-model="taskData.taskAddr"
          :data="dataComplete"
          @on-search="showAddresses"
          placeholder="Input address,support fuzzy query"
          clearable
        >
        <Option v-for="item in dataComplete" :value="JSON.stringify({location: item.name, longitude: item.location.split(',')[0], latitude: item.location.split(',')[1]})" :key="item.id">
          <p>地名：{{item.name}}</p>
          <p>地址：{{item.address}}</p>
        </Option>
        </AutoComplete>
      </FormItem>

      <FormItem label="publishDate" prop="publishDate">
        <DatePicker type="datetime" :options="publishoptions" @on-change="changePublishDate" placeholder="Select publish time" style="width: 200px"/>
      </FormItem>
      <FormItem label="deadline" prop="completeDate">
        <DatePicker type="datetime" :options="completeoptions" @on-change="changeCompleteDate" placeholder="Select deadline" style="width: 200px"/>
      </FormItem>
      <FormItem label="answer count" prop="answersLength">
        <InputNumber :min="1" v-model="taskData.answersLength" />
      </FormItem>
      
      <!-- <FormItem label="是否可以重复回答" prop="taskRepeat">
        <Input type="text" v-model="taskData.taskRepeat" />
      </FormItem> -->


      <FormItem label="reward" prop="taskPrice">
        <Input type="text" v-model="taskData.taskPrice" />
      </FormItem>
      <FormItem
        v-for="(item, index) in taskData.questions"
        v-if="item.status"
        :key="index"
        :label="'question'+item.index"
        :prop="'questions.'+index+'.value'"
        :rules="{required: true, message: '问题不可以为空', trigger: 'blur'}"
      >
        <Row>
          <Col span="18">
            <Input type="text" v-model="item.value"/>
          </Col>
          <Col span="4" offset="1">
            <Button type="ghost" @click="handleRemove(index)">delete</Button>
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
        <Button type="dashed" @click="addOptions(index)" icon="plus-round">addOption</Button>
        </FormItem>
      </FormItem>
      <FormItem>
        <Button type="dashed" long @click="addQuestions" icon="plus-round">addQuestion</Button>
      </FormItem>
      <!-- 添加问卷ID -->
      
     <!--  <FormItem
        :key="index"
        :label="问卷调查ID"
      >     
      </FormItem>

      <FormItem>
        <Button type="dashed" @click="addQuestionnaire" icon="plus-round">添加问卷ID</Button>
      </FormItem> -->
      <FormItem>
        <Row>
          <Col span="10">
            <Button :loading="loading" type="primary" @click="handleSubmit('task')" long>publish</Button>
          </Col>
          <Col span="10" offset="2">
            <Button type="ghost" @click="handleReset('task')" long>reset</Button>
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
</template>

<script>
import jsonp from 'jsonp';
import { mapState } from 'vuex';
export default {
  name: 'AddTask',
  data(){
    return {
      index: 1,
      dataComplete: [],
      tempValue: '',
      //inputvisible:false,
      taskData: {
        taskName: '',
        taskDesc: '',
        questionnaireId:'',
        taskAddr: '',
        publishDate: '',
        completeDate: '',
        taskPrice: '',
        answersLength: '',
        //taskRepeat:'',
        questionnaire:'',
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
            required: true, message: '必须输入任务类型'
          }
        ],
        taskDesc: [
          {
            required: true, message: '任务描述应不少于一个字符'
          }
        ],
        taskAddr: [
          {
            required: true, message: '请为任务选择一个地址'
          }
        ],
        publishDate: [
          {
            required: true, message: '请为任务选择发布时间'
          }
        ],
        completeDate: [
          {
            required: true, message: '请为任务选择截止时间'
          }
        ],
        answersLength: [
          {
            required: true, message: "请输入一个合法的期望回答数"
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
  publishoptions: {
              disabledDate (date) {
                return date && date.valueOf() < Date.now() - 86400000;
              }
            },
  completeoptions: {
              disabledDate (date) {
                return date && date.valueOf() < Date.now() - 86400000;
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
      this.taskData.questions.splice(index, 1); // 删除对应index的问题
    },
    removeOption(index, index1) {
      this.taskData.questions[index].options.splice(index, 1); //
    },
   
    changePublishDate(date) {
      this.taskData.publishDate = date; 
    },
    changeCompleteDate(date) {
      this.taskData.completeDate = date;
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
          this.$store.dispatch('addTask', { data: this.taskData }).then((json) =>{
            alert("任务发布成功！");
            this.$router.push('list_task');
            //this.$Message.info('发布任务成功');
            //this.$refs[name].resetFields();
          }).catch(err =>{
            console.log(err);
          });

        }else {
          // 验证成功的一些提示
          alert("啊哦。。。任务发布失败，请重新填写！");
          this.$refs[name].resetFields(); 
        }
      })
    },
    handleReset(name) {
      this.$refs[name].resetFields(); // 清空所有组件
    }
  },
  computed: {
    loading() {
      return this.$store.state.task.updateing
    },
    ...mapState({
      error: state => state.task.error
    })
  }
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
    width: 70%;
    left: 15%;
    top: 10%;
  }
</style>
