<template>
  <div class="my-books">
    <!-- 添加组件内容 -->
    <h2 class="el-dialog__title">租书系统</h2>
    <div class="table_1">
      <el-table :data="tableData" border style="width: 100%" class="el-table">
        <el-table-column prop="id" label="图书编号" width="150" class="el-table__expand-column"></el-table-column>
        <el-table-column prop="title" label="图书名称" width="120"></el-table-column>
        <el-table-column prop="author" label="作者" width="120"></el-table-column>
        <el-table-column prop="bookClassification" label="分类" width="120"></el-table-column>
        <el-table-column prop="bookPrice" label="价格" width="120"></el-table-column>
        <el-table-column prop="membershipPrice" label="会员价格" width="120"></el-table-column>
        <el-table-column label="操作" width="100">
          <template v-slot="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看章节</el-button>
            <el-button @click="buybooks(scope.row)">购买</el-button><!--修改-->
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { createdlist, lookschapter, placeanorder, queryMeBooks, updatemenoy } from '@/api/books'

export default {
  name: 'MyBooks',
  components: {
    // 注册组件
  },
  props: {
    // 定义props
  },
  data() {
    return {
      tableData:[{
        id:'',
        title:'',
        author:'',
        bookClassification:'',
        bookPrice:'',
        membershipPrice:''
      }]
    }

  },
  computed: {
    // 计算属性
  },
  watch: {
    // 监听数据变化
  },
  methods: {
    /* 卖书下单 */
    placeanorder() {
      placeanorder(this.form).then(res => {
        if (res) {
          this.queryBooks()
        }
      })

    },
    // 组件方法
    /* 查看章节数 */
    handleClick(row) {
      lookschapter(row).then(res => {
        if (res) {
          this.mybooks = res.data
        }
      })
    },
    buybooks(row) {
      this.dialogVisible = true
      console.log(row)
      this.formd = row
    },
    /* 提交下单,展示下单列表 */
    onSubmit() {

    },
    queryBooks() {
      createdlist().then(res => {
        if (res) {
          this.tableData = res.data
        }
      })
    },
    /* 我的图书 */
    queryMeBooks() {
      queryMeBooks(this.mybooks).then(res => {
        if (res) {
          this.mybooks = res.data
        }

      })
    },
    /* 充值书币 */
    updatemenoy() {
      this.booksmomey = true

    },
    updatemenoybm() {
      updatemenoy(this.formd).then(res => {
        if (res) {
          alert('成功充值')
          this.booksmomey = false
        }
      })
    },
    /* 开通会员 */
    vip() {
      vip().then(res => {
        if (res) {

        }
      })
    }
  },

  created() {
    // 生命周期钩子 - created
    this.queryBooks()
  }
}
</script>

<style scoped>
.my-books {
  /* 添加样式 */
}

/* 全局样式 */
body {
  font-family: Arial, sans-serif;
}

.el-form-item .el-form-label {
  width: 100px;
  text-align: right;
}

.el-form-item .el-input input,
.el-form-item .el-select input,
.el-form-item .el-date-editor input {
  border-radius: 4px; /* 输入框倒角处理 */
  padding: 6px 10px;
  border: 1px solid #ccc;
  background-color: #f9f9f9;
}

.el-form-item .el-input input:focus,
.el-form-item .el-select input:focus,
.el-form-item .el-date-editor input:focus {
  outline: none;
  border-color: #409eff; /* 输入框获取焦点时的边框颜色 */
}

.el-form-item .el-select__caret {
  color: #909399; /* 下拉箭头颜色 */
}

.el-form-item.is-error .el-input input,
.el-form-item.is-error .el-select input,
.el-form-item.is-error .el-date-editor input {
  border-color: #f56c6c; /* 错误状态下的边框颜色 */
}

.el-dialog__title {
  white-space: break-spaces;
  mso-height-source: auto;
  width: 400px;
  height: auto;
  background-color: #20a0ff;
  color: #333; /* 标题文字颜色 */
}

.el-dialog__footer .el-button:not(:last-child) {
  margin-right: 10px;
}
</style>
