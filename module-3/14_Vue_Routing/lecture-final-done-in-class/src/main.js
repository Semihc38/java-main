import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'  // Access the Vue Router code - add when use the router on create

Vue.config.productionTip = false

new Vue({  // Instantiate a Vue object
  store,   // Tell Vue you are using Vuex
  router,  // Tell Vue you are using the router
  render: h => h(App)  // Tell Vue your main page is App.vue
}).$mount('#app')   // The Vue stuff will be placed in the html at the component with id='app'
