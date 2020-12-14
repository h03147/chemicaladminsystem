import Vue from 'vue'
import './plugins/axios'
import './styles/index.scss' // global css
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import globalVariable from './api/global_variable.js'
import "./reset.css";
import moment from 'moment'


Vue.prototype.GLOBAL = globalVariable //全局引入 baseURL等常量

Vue.config.productionTip = false

Vue.filter('dateformat', function(dataStr, pattern = 'YYYY-MM-DD HH:mm:ss') {

  return moment(dataStr).format(pattern)
  
  })
  

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

