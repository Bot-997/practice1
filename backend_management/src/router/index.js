import Vue from 'vue'
import VueRouter from 'vue-router'
import Router from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home',
    meta: {title: '首页'},
    component: () => import('../views/Manager.vue'),
    children: [{
      path: 'home',
      name: 'home',
      meta: {title: '首页'},
      component: () => import('../views/Home.vue')
    }]
  },
  {
    path: '/sysManage',
    name: 'sysManage',
    meta: {title: '系统管理'},
    redirect: '/sysManage/userManage',
    component: () => import('../views/Manager.vue'),
    children: [
      { path: 'userManage',
        name: 'userManage',
        meta: {title: '用户管理'},
        component: () => import('../components/UserData.vue')
      },
    ],
  },
  {
    path: '/login',
    name: 'login',
    meta: {title: '登录'},
    component: () => import('../views/Login.vue'),
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const originalPush = Router.prototype.push
Router.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export default router
