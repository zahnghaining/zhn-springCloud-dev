<template>
  <div class="container">
    <div class="header">
      <!-- 头部内容 -->
<h1>淘宝商城</h1>
    </div>
    <div class="main">
      <div class="sidebar">
        <!-- 侧边栏内容 -->
        <ul>
          <li v-for="store in stores" :key="store.id">
            {{ store.name }}
          </li>
        </ul>
      </div>
      <div class="content">
        <div class="card-list">
          <!-- 商品卡片列表 -->
          1<div v-for="item in productList" :key="item.id" class="card">
            2<div class="card-image">
              <img :src="item.imageUrl" alt="">
            </div>
            3<div class="card-info">
             4 <div class="card-title">{{ item.title }}</div>
            5  <div class="card-price">￥{{ item.price }}</div>
             6 <div class="card-action">
               7 <button class="add-cart-btn" @click="addToCart(item)">加入购物车</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="footer">
      <!-- 底部内容 -->
    </div>
  </div>
</template>

<script>
// 获取 header 元素
const header = document.querySelector('header');

// 监听页面滚动事件
window.addEventListener('scroll', function() {
  // 获取当前滚动的距离
  const scrollY = window.scrollY;

  // 判断滚动的距离是否超过了 header 元素的位置
  if (scrollY >= header.offsetTop) {
    // 如果超过了，为 header 元素添加 sticky 类
    header.classList.add('sticky');
  } else {
    // 如果没超过，移除 sticky 类
    header.classList.remove('sticky');
  }
});
export default {
  data() {
    return {
      stores: [
        { id: 1, name: 'Store A' },
        { id: 2, name: 'Store B' },
        { id: 3, name: 'Store C' }
      ],
      productList: [
        {
          id: 1,
          title: '商品1',
          price: 100,
          imageUrl: 'https://picsum.photos/id/1000/200/200'
        },
        {
          id: 2,
          title: '商品2',
          price: 200,
          imageUrl: 'https://picsum.photos/id/1001/200/200'
        },
        {
          id: 3,
          title: '商品3',
          price: 300,
          imageUrl: 'https://picsum.photos/id/1002/200/200'
        }
      ],
      cartList: []
    }
  },
  methods: {
    addToCart(item) {
      this.cartList.push(item)
      // TODO: 添加到购物车的业务逻辑
    }
  }
}
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  background-color: #fff;
  /* 其他样式属性 */
}

.header.sticky {
  /* 添加样式，例如将背景色变为深色 */
  background-color: #333;
}

.main {
  flex: 1;
  display: flex;
}

.sidebar {
  /* 侧边栏样式 */
  width: calc(33.33% - 10px);
  margin-bottom: 20px;
  box-shadow: 0 0 5px #ccc;
  border-radius: 5px;
  height: 100%;
}

.content {
  flex: 1;
  padding: 20px;
  margin-left: 100px;
}

.card-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.card {
  width: calc(33.33% - 10px);
  margin-bottom: 20px;
  box-shadow: 0 0 5px #ccc;
  border-radius: 5px;
}

.card-image {
  width: 100%;
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.card-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.card-info {
  padding: 10px;
}

.card-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
}

.card-price {
  color: red;
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

.card-action {
  display: flex;
  justify-content: center;
  align-items: center;
}

.add-cart-btn {
  background-color: #f60;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
}
</style>
