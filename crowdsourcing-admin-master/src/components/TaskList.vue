<template>
  <div class="task-list-container">
    <Spin v-if="loading"></Spin>
    <Table width="100%" border :columns="columns" :data="tasks"></Table>
    <Modal
      v-model="modalShow"
      title="任务详情"
      @on-ok="closeModal"
      @on-cancel="closeModal"
    >
      <p class="title">基本信息</p>
      <p>任务类型： {{selectedTask.task_name}}</p>
      <p>任务描述： {{selectedTask.desc}}</p>
      <p>发布时间： {{selectedTask.publish_time}}</p>
      <p>截止时间： {{selectedTask.end_time}}</p>
      <p class="title">问题描述</p>      
      <ul class="question-list">
        <li v-for="(question, index) in selectedTask.task_question" :key="index">
          <p>问题{{index+1}}：{{question.description}}</p>
          <p class="sub-title">回答选项</p>
          <ul class="option-list">
            <li v-for="(option, index1) in question.label" :key="Math.random()*index1">
              <p>选项{{index1+1}}：{{option.labledescription}}</p>
            </li>
          </ul>
        </li>
      </ul>
    </Modal> 
    <Alert style="margin-top: 10px" type="error" v-if="error" closable>{{error}}</Alert>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'TaskList',
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
          title: '类型',
          key: 'task_name',
          width: 100
        },
        {
          title: '任务描述',
          key: 'desc',
          width: 300
        },
        {
          title: '发布时间',
          key: 'publish_time',
          width: 150
        },
        {
          title: '截止时间',
          key: 'end_time',
          width: 150
        },
        {
          title: '期望回答数',
          key: 'expect_answer_count',
          width: 100
        },
        {
          title: '实际回答数',
          key: 'actual_answer_count',
          width: 100
        },
        {
          title: '任务状态',
          key: 'task_status',
          width: 100
        },
        {
          title: '任务进度',
          key: 'task_progress',
          width: 90
        },
        {
          title: '操作',
          key: 'action',
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
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.show(params.index)
                  }
                }
              }, '查看')
/*             h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.remove(params.index)
                  }
                }
              }, '删除')*/
            ])
          }
        }
      ]
    }
  },  
  methods: {
    show(index) {
      this.index = index;
      this.modalShow = true;
    },
    remove(index) {
      this.tasks.splice(index, 1);
    },
    closeModal() {
      this.modalShow = false;
    }
  },
  computed: {
    ...mapState({
      tasks: (state) => state.task.tasks,
      loading: (state) => state.task.loading,
      error: (state) => state.task.loadError
    }),
    selectedTask(){
      return this.tasks[this.index];
    }
  },
  mounted() {
    this.$store.dispatch('loadTasks'); // 加载任务数据
  }
}


</script>

<style scoped>
.task-list-container {
  position: relative;
  width: 80%;
  left: 5%;
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
.sub-title {
  font-weight: lighter;
}
.option-list {
  list-style: none;
  display: flex;
}
.option-list > li {
  margin-right: 10px;
  font-size: 12px;
  font-weight: normal;
}
</style>
