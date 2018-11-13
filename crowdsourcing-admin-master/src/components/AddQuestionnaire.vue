<template>
  <div class="questionnaire-wrapper">
    <Form ref="questionnaire" :model="questionnaireData" :rules="ruleValidate" :label-width="80">
      <FormItem label="问卷编号" prop="questionnaireDataNum">
        <Input type="text" style="width: 100px" v-model="questionnaireData.questionnaireDataNum" />
      </FormItem>
      <FormItem label="问卷关联任务编号" prop="questionnaireTaskNum">
        <Input type="text" style="width: 100px" v-model="questionnaireData.questionnaireTaskNum" />
        注：如与任务不关联，可以不用填写。
      </FormItem>
      <FormItem label="问卷描述" prop="questionnaireDataDesc">
        <Input type="textarea" v-model="questionnaireData.questionnaireDataDesc" />
      </FormItem>
      <FormItem label="地址" prop="questionnaireDataAddr">
        <AutoComplete
          v-model="questionnaireData.questionnaireDataAddr"
          :data="dataComplete"
          @on-search="showAddresses"
          placeholder="输入地址，支持模糊查询"
          clearable
        >
        <Option v-for="item in dataComplete" :value="JSON.stringify({location: item.name, longitude: item.location.split(',')[0], latitude: item.location.split(',')[1]})" :key="item.id">
          <p>地名：{{item.name}}</p>
          <p>地址：{{item.address}}</p>
        </Option>
        </AutoComplete>
      </FormItem>
      <FormItem label="问卷是否显示" prop="questionnaireDataShow">
        <Select v-model="questionnaireData.questionnaireDataShow" clearable style="width:200px">
          <Option v-for="item in showList" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </FormItem>
      
      <FormItem label="发布时间" prop="publishDate">
        <DatePicker type="datetime" :options="publishoptions" @on-change="changePublishDate" placeholder="选择发布时间" style="width: 200px"/>
      </FormItem>
      <FormItem label="结束时间" prop="completeDate">
        <DatePicker type="datetime" :options="completeoptions" @on-change="changeCompleteDate" placeholder="选择截止时间" style="width: 200px"/>
      </FormItem>
      <FormItem label="期待回答数" prop="answersLength">
        <InputNumber :min="1" v-model="questionnaireData.answersLength" />
      </FormItem>
      

      <FormItem
        v-for="(item, index) in questionnaireData.questions"
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
        <Row>
          <Col span="10">
            <Button :loading="loading" type="primary" @click="handleSubmit('questionnaire')" long>发布问卷</Button>
          </Col>
          <Col span="10" offset="2">
            <Button type="ghost" @click="handleReset('questionnaire')" long>重新填写</Button>
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
  name: 'addQuestionnaire',
  data(){
    return {
      index: 1,
      dataComplete: [],
      tempValue: '',
      questionnaireData: {
        questionnaireDataNum: '',
        questionnaireTaskNum: '',
        questionnaireDataDesc: '',
        questionnaireDataAddr: '',
        questionnaireDataShow:'',
        publishDate: '',
        completeDate: '',
        answersLength: '',
        //questionnaireDataRepeat:'',
        questions: [
          {
            value: '',
            index: 1,
            status: 1,
            options: [],
          }
        ]
      },
      showList:[
        {
          value: 'yes',
          label: '是'
        },
        {
          value:'no',
          label:'否'
        }
      ],
      ruleValidate: {
        questionnaireDataNum: [
          {
            required: true, message: '必须输入问卷编号'
          }
        ],
        questionnaireDataDesc: [
          {
            required: true, message: '问卷描述应不少于一个字符'
          }
        ],
        questionnaireDataAddr: [
          {
            required: true, message: '请为问卷调查选择一个地址'
          }
        ],
        questionnaireDataShow: [
          {
            required: true, message: '请说明问卷调查是否显示'
          }
        ],
        publishDate: [
          {
            required: true, message: '请为问卷选择发布时间'
          }
        ],
        completeDate: [
          {
            required: true, message: '请为问卷选择截止时间'
          }
        ],
        answersLength: [
          {
            required: true, message: "请输入一个合法的期望回答数"
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
      this.questionnaireData.questions.splice(index, 1); // 删除对应index的问题
    },
    removeOption(index, index1) {
      this.questionnaireData.questions[index].options.splice(index, 1); //
    },
    changePublishDate(date) {
      this.questionnaireData.publishDate = date; 
    },
    changeCompleteDate(date) {
      this.questionnaireData.completeDate = date;
    },
    addQuestions() {
      this.index ++;
      this.questionnaireData.questions.push({
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
          this.questionnaireData.questions[index].options.push(this.tempValue);
        }
      })
    },
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {

        if(valid) {
          // 验证成功，向后端发起请求
          this.$store.dispatch('addQuestionnaire', { data: this.questionnaireData }).then((json) =>{
            alert("问卷发布成功！");
            this.$router.push('list_task');
            //this.$Message.info('发布任务成功');
            //this.$refs[name].resetFields();
          }).catch(err =>{
            console.log(err);
          });

        }else {
          // 验证成功的一些提示
          alert("啊哦。。。问卷发布失败，请重新填写！");
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
      return this.$store.state.questionnaire.updateing
    },
    ...mapState({
      error: state => state.questionnaire.error
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
  //   console.log(this.questionnaireData.questionnaireDataAddr);
  // }
}



</script>

<style scoped>
  .questionnaire-wrapper {
    position: relative;
    width: 70%;
    left: 15%;
    top: 10%;
    height: 700px;
    overflow: auto;
  }
</style>
