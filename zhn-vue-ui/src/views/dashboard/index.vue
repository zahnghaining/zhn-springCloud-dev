<template>
  <div class="dashboard-container">
    <div class="dashboard-text">欢迎: {{ name}}!!
    {{avatar}}
    {{phone }}
    {{emil}}

    </div>
    <div class="container">
      <div class="box" @click="chooseBox(1)">书籍系统</div>
      <div class="box" @click="chooseBox(2)">店铺</div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'phone',
      'emil'
    ])
  },
  methods: {
    chooseBox(v){
      if (v==1){
        alert(v+'正在前往租书系统')
        this.$router.push({name:'Books'})
      }
      if (v==2){
        alert(v+'正在前往电商系统')
        this.$router.push({name:'E-commerce'})
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
/* 使 .container 充满整个页面 */
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}

/* 将 .container 设置为 flex 容器，垂直和水平居中 */
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

/* 两个 div */
.box {
  width: 100px;
  height: 100px;
  margin: 10px;
  text-align: center;
  line-height: 100px;
  cursor: pointer;
  background-color: #ccc;
  border-radius: 5px;
}

/* 鼠标悬停时改变背景颜色 */
.box:hover {
  background-color: #ddd;
}
</style>
