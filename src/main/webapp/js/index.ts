import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

import axios from "axios";
import MainPage from "./components/MainPage.vue";
import LoginPage from "./components/LoginPage.vue";
import AccountRegistrationPage from "./components/AccountRegistrationPage.vue";

const addConfig: any = axios.create({
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json; charset=UTF-8'
  }
})

const NotFound: any = { template: '<p>Page not found</p>' };

const router: any = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/kasan/', component: MainPage },
    { path: '/kasan/account_registration', component: AccountRegistrationPage },
    { path: '/kasan/login', component: LoginPage },
    { path: '/kasan/logout', beforeEnter: logout }
  ] || NotFound
});

function logout(to: any, from: any, next: any):void {
  addConfig.post("api/authentication/logout")
  .then(() => {
    next('/kasan/login');
  })
  .catch((e: any) => {
    alert("ログアウトに失敗しました。");
  })
};

const vm: any = new Vue({
  el: "#app",
  router,
  render: h => h({template: '<transition name="fade" mode="out-in"><router-view></router-view></transition>'})
});
