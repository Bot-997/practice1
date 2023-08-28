import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Manager.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',
    children: [
      { path: '/home', name: '首页', component: () => import('../views/Home.vue') },
      { path: '/userData', name: '用户管理', component: () => import('../views/UserData.vue') }
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
