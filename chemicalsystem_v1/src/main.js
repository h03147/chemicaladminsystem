import Vue from 'vue'
import './plugins/axios'
import './styles/index.scss' // global css
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import globalVariable from './api/global_variable.js'

Vue.prototype.GLOBAL = globalVariable //全局引入 baseURL等常量

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

