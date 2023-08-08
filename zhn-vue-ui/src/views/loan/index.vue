<template>
  <div class="loan">
    <h1>申请贷款</h1>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="选择车型" label-width="180px">
        <el-select v-model="form.cartype" placeholder="请选择" @change="onCarTypeChange">
          <el-option v-for="item in carType" :key="item.id" :label="item.brand" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择分期年份" label-width="200px">
        <el-radio-group v-model="form.radio">
          <el-radio label="1">12</el-radio>
          <el-radio label="2">24</el-radio>
          <el-radio label="3">36</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">买车</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getVehicleInformation } from '@/api/loan'

export default {
  name: 'Loan',
  data() {
    return {
      carType: [],
      form: {
        cartype: '',
        radio: ''
      }
    }
  },
  methods: {
    onSubmit() {
      // 根据需要处理提交逻辑
      console.log('提交表单', this.form);
      // 进入平台利率计算页面
      this.$router.push('/interest-calculation');
    },
    onCarTypeChange() {
      // 根据选择的车型，更新表单中的相关字段
      const selectedCarType = this.carType.find(item => item.id === this.form.cartype);
      if (selectedCarType) {
        this.form.brand = selectedCarType.brand;
        this.form.model = selectedCarType.model;
        this.form.licensePlate = selectedCarType.licensePlate;
        this.form.price = selectedCarType.price;
      } else {
        this.form.brand = '';
        this.form.model = '';
        this.form.licensePlate = '';
        this.form.price = '';
      }
    },
    initializeCarPurchase() {
      getVehicleInformation().then(res => {
        if (res && res.data) {
          this.carType = res.data;
        }
      }).catch(error => {
        console.error('无法获取车辆信息:', error);
      });
    }
  },
  created() {
    this.initializeCarPurchase();
  }
};
</script>
<style scoped>
.my-component {
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
