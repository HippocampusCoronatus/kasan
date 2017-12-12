import Vue from "vue";
import MainPage from "./components/MainPage.vue";

let vm = new Vue({
  el: "#app",
  template: `
    <main-page />
  `,
  components: {
      MainPage
  }
});
