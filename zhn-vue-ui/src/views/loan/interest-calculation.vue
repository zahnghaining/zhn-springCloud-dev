<template>
  <div class="Interest-calculation">
    <!-- 添加组件内容 -->
    <h2>利率计算</h2>



  </div>
</template>

<script>
const socket = new WebSocket('ws://localhost:8080/timer')

socket.onmessage = function(event) {
  const data = event.data
  // 处理计时数据
  console.log("Received: " + data);
};

socket.onclose = function(event) {
  // WebSocket连接关闭处理
  console.log("Connection closed");
};
export default {
  name: 'Interest-calculation',
  components: {
    // 注册组件
  },
  props: {
    // 定义props
  },
  data() {
    return {
      // 初始化数据
    };
  },
  computed: {
    // 计算属性
  },
  watch: {
    // 监听数据变化
  },
  methods: {
    // 组件方法
  },
  created() {
    // 生命周期钩子 - created
  },
  mounted() {
    // 生命周期钩子 - mounted
  },
  beforeCreate() {
    // 生命周期钩子 - beforeCreate
  },
  beforeMount() {
    // 生命周期钩子 - beforeMount
  },
  beforeUpdate() {
    // 生命周期钩子 - beforeUpdate
  },
  updated() {
    // 生命周期钩子 - updated
  },
  beforeDestroy() {
    // 生命周期钩子 - beforeDestroy
  },
  destroyed() {
    // 生命周期钩子 - destroyed
  },
  activated() {
    // 生命周期钩子 - activated
  },
};
</script>

<style scoped>
.Interest-calculation {
  /* 添加样式 */
}
/* 全局样式 */
body {
  font-family: Arial, sans-serif;
}

/* 表单样式 */
.el-form {
  margin-bottom: 20px;
}

.el-form-item {
  margin-bottom: 15px;
  color: #333; /* 字体颜色 */
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

/* 按钮样式 */
.el-button {
  border-radius: 4px; /* 按钮倒角处理 */
  padding: 10px 20px;
  font-weight: bold;
}

.el-button--primary {
  background-color: #409eff; /* 主要按钮背景颜色 */
  color: #fff; /* 主要按钮文字颜色 */
}

.el-button--primary:hover {
  background-color: #66b1ff; /* 鼠标悬停时的背景颜色 */
}

.el-button--danger {
  background-color: #f56c6c; /* 危险按钮背景颜色 */
  color: #fff; /* 危险按钮文字颜色 */
}

.el-button--danger:hover {
  background-color: #ff7875; /* 鼠标悬停时的背景颜色 */
}

/* 弹出框样式 */
.el-dialog {
  border-radius: 4px; /* 弹出框倒角处理 */
}

.el-dialog__header {
  border-bottom: none; /* 去除弹出框头部的边框 */
  padding: 10px 20px;
  background-color: #f5f7fa; /* 头部背景颜色 */
}

.el-dialog__title {
  color: #333; /* 标题文字颜色 */
}

.el-dialog__body {
  padding: 20px;
}

.el-dialog__footer {
  border-top: none; /* 去除弹出框底部的边框 */
  padding: 10px 20px;
  text-align: right;
}

.el-dialog__footer .el-button:not(:last-child) {
  margin-right: 10px;
}
</style>

