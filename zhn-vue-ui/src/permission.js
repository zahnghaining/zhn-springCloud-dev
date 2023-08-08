import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // 无重定向白名单

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()

// 判断是否有 token
  if (!hasToken) {
    if (whiteList.includes(to.path)) {
      // 白名单中的路由直接访问
      next()
    } else {
      // 其他路由跳转到登录页面
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
    return
  }

  // 判断是否已经登录
  if (to.path === '/login') {
    // 如果已经登录，重定向到首页
    next({ path: '/' })
    NProgress.done()
    return
  }

  // 判断是否已经获取用户信息
  const hasGetUserInfo = store.getters.name
  if (hasGetUserInfo) {
    next()
  } else {
    try {
      // 获取用户信息
      await store.dispatch('user/getInfo')
      next()
    } catch (error) {
      // 如果获取用户信息失败，删除 token 并跳转到登录页面
      await store.dispatch('user/resetToken')
      Message.error(error || 'Has Error')
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})


router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
