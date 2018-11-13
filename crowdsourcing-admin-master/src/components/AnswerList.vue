<template>
  <div class="answer-wrapper">
    <!-- <Spin v-if="loading"></Spin> -->
    <Table width="100%" border :columns="columns" :data="answerList"></Table>
    <Alert style="margin-top: 10px" type="error" v-if="error" closable>{{error}}</Alert>
    <Modal
      title="回答详情"
      v-model="modalShow"
      @on-ok="closeModal"
      @on-cancel="closeModal"
    >
      <p class="title">基本信息</p>
      <p>任务类型：{{currentQuestion.task_type}}</p>
      <p>任务描述：{{ currentQuestion.question }}</p>
      <p class="title">回答详情</p>
      <ul class="question-list">
        <li v-for="(answer, index) in currentQuestion.answer" :key="index">
          <p>问题{{index+1}}：{{answer.description}}</p>
          <p class="answer-item">回答：{{answer.labeldescription}}</p>
        </li>
        <p class="title">图片预览</p>
        <br><img width='150px' height='150px' :src='currentQuestion.pic' alt='答案上传照片凭证'/>
      </ul>
    </Modal>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'AnswerList',
  data() {
    return {
      modalShow: false,
      index: 0,
      columns: [
        {
          title: '任务编号',
          key: 't_id',
          fixed: 'left',
          width: 100
        },
        {
          title: '任务类型',
          key: 'task_type',
          width: 100
        },
        {
          title: '用户编号',
          key: 'u_id',
          width: 100
        },
        {
          title: '提交答案位置',
          key: 'publish_place',
          width: 200
        },
        {
          title: '答案描述',
          key: 'answer_desc',
          width: 200
        },
        {
          title: '回答时间',
          key: 'answer_time',
          width: 200
        },
        {
          title: '图片路径',
          key: 'pic',
          width: 300
        },
        {
          title: '详情',
          key: 'more',
          width: 150,
          fixed: 'right',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.show(params.index)
                  }
                }
              }, '详情')
            ])
          }
        }
      ],
      answers: [
        {
          t_id: '001',
          question: "万达广场附近的打折信息",
          task_type: '打折',
          u_id: '0001',
          publish_place: '舜华路1500号',
          answer_desc: 'UME优惠50%',
          answer_time: '2018-2-17 13:10',
          pic: '../static/img.jpg'
        },
        {
          t_id: '001',
          question: "万达广场附近的打折信息",
          task_type: '打折',
          u_id: '0002',
          publish_place: '舜华路1500号',
          answer_desc: 'XXX自助烧烤7折优惠',
          answer_time: '2018-3-4 20:00',
          pic: '../static/img.jpg'
        }
      ]
    }
  },
  methods: {
    show(index) {
      this.index = index;
      this.modalShow = true;
    },
    closeModal() {
      this.modalShow = false;
    }
  },
  computed: {
    ...mapState({
      answerList: (state) => state.answer.data,
      error: (state) => state.answer.error
    }),
    currentQuestion() {
      return this.answerList[this.index];
    }
  },
  mounted() {
    this.$store.dispatch('loadAnswers'); // 加载回答数据
  }
}
</script>

<style scoped>
.answer-wrapper{
  position: relative;
  left: 5%;
  width: 80%;
  top: 20px;
  height: 600px;
  overflow: auto;
}
.title {
  width: 100%;
  font-size: 16px;
  border-bottom: 1px solid rgba(0,0,0,.08);
  padding: 5px;
  margin-bottom: 20px;
}

.question-list > li{
  list-style: none;
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 20px;
}
.answer-item {
  font-size: 12px;
  font-weight: normal;
}
</style>


