import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/signin',
    name: 'Signin',
    component: () => import(/* webpackChunkName: "about" */ '../views/signin/index')
  },
  {
    path: '/homepage',
    name: 'HomePage',
    component: () => import(/* webpackChunkName: "about" */ '../views/homepage/index'),

    children: [
      {
        path: 'page1index1',
        name: 'Page1Index1',
        component: () => import('../views/homepage/page1/index1')
      },
      {
        path: 'page2index1',
        name: 'Page2Index1',
        component: () => import('../views/homepage/page2/index1')
      },
      {
        path: '/page2update1',
        name: 'Page2Update1',
        component: () => import('../views/homepage/page2/update1')
      },
      {
        path: 'page3index1',
        name: 'Page3Index1',
        component: () => import('../views/homepage/page3/index1')
      },
      {
        path: 'page3index2',
        name: 'Page3Index2',
        component: () => import('../views/homepage/page3/index2')
      },
      {
        path: 'page4index1',
        name: 'Page4Index1',
        component: () => import('../views/homepage/page4/index1')
      },
      {
        path: 'page4index2',
        name: 'Page4Index2',
        component: () => import('../views/homepage/page4/index2')
      },
      {
        path: 'page4index3',
        name: 'Page4Index3',
        component: () => import('../views/homepage/page4/index3')
      },
      {
        path: '/page4adddate1',
        name: 'Page4Adddate1',
        component: () => import('../views/homepage/page4/adddate1')
      },
      {
        path: 'page5index1',
        name: 'Page5Index1',
        component: () => import('../views/homepage/page5/index1')
      },
      {
        path: 'page5index2',
        name: 'Page5Index2',
        component: () => import('../views/homepage/page5/index2')
      },
      {
        path: 'page6index1',
        name: 'Page6Index1',
        component: () => import('../views/homepage/page6/index1')
      },
      {
        path: 'page7index1',
        name: 'Page7Index1',
        component: () => import('../views/homepage/page7/index1')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
