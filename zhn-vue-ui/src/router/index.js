//引用vue
import Vue from 'vue'
//引用路由器
import Router from 'vue-router'
//Router注册到vue中使用
Vue.use(Router)

/* Layout */
import Layout from '@/layout'
//常量路线
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: {
        title: '首页',
        icon: 'dashboard',
        guest: true
      }
    }]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: {
      title: '沭云管理平台',
      icon: 'el-icon-s-help',
      guest: true
    },
    children: [
      {
        path: 'books',
        name: 'Books',
        component: () => import('@/views/books/index'),
        meta: {
          title: '租书系统',
          icon: 'books',
          guest: true
        }
      },
      {
        path: 'e-commerce',
        name: 'E-commerce',
        component: () => import('@/views/e-commerce/index'),
        meta: {
          title: '电商系统',
          icon: 'e-commerce',
          guest: true
        }
      },
      {
        path: 'loan',
        name: 'Loan',
        component: () => import('@/views/loan/index'),
        meta: {
          title: '贷款系统',
          icon: 'loan',
          guest: true
        }
      },
      {
        path: '/interest-calculation',
        name: 'Interest-calculation',
        component: () => import('@/views/loan/interest-calculation'),
        meta: {
          title: '利率界面',
          icon: 'interest-calculation',
          guest: true
        }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]
//创建路由器
const createRouter = () => new Router({
  //模式：“历史记录”,
  // 需要服务支持
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

//一处创建多处引用
const router = createRouter()

// 重置路由器
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher
}

export default router
