/* eslint no-console: 0 */
// Run this example by adding <%= javascript_pack_tag 'hello_vue' %> (and
// <%= stylesheet_pack_tag 'hello_vue' %> if you set extractStyles to true
// in config/webpack/loaders/vue.js) to the head of your layout file,
// like app/views/layouts/application.html.erb.
// All it does is render <div>Hello Vue</div> at the bottom of the page.

import Vue from 'vue'
import VueRouter from 'vue-router'
import App from '../src/App.vue'
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/ja'

Vue.use(VueRouter)
Vue.use(ElementUI, { locale })

import FilmIndex from '../src/FilmIndex.vue'
import FilmCreate from '../src/FilmCreate.vue'
import FilmDetail from '../src/FilmDetail.vue'
import CustomerIndex from '../src/CustomerIndex.vue'

const router = new VueRouter({
  routes:  [
    { path: '/', component: FilmIndex },
    { path: '/films/new', component: FilmCreate },
    { path: '/films/:id', component: FilmDetail },
    { path: '/customers', component: CustomerIndex },
  ]
})

document.addEventListener('DOMContentLoaded', () => {
  new Vue({
    router,
    render: h => h(App),
  }).$mount('#app')
})