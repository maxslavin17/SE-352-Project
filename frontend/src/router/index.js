import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manage.vue'),
    redirect: "/home",
    children: [
      { path: 'home', name: 'Home', component: () => import('../views/Home.vue')},
      { path: 'user', name: 'User', component: () => import('../views/User.vue')},
      { path: 'person', name: 'Person', component: () => import('../views/Person.vue')},
      { path: 'role', name: 'Role', component: () => import('../views/Role.vue')},
      { path: 'menu', name: 'Menu', component: () => import('../views/Menu.vue')},
      { path: 'course', name: 'Course', component: () => import('../views/Course.vue')},
      { path: 'mycourse', name: 'MyCourse', component: () => import('../views/MyCourse.vue')},
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)
  store.commit("setPath")
  next()
})

export default router