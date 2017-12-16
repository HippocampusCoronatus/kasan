<template>
  <section>
    <h1>会員登録テスト</h1>
    <article>
      <h1>会員登録情報入力</h1>
      <div>メールアドレス<input type="text" v-model="eMail" /></div>
      <div>パスワード<input type="password" v-model="password" /></div>
      <div>名前<input type="text" v-model="name" /></div>
      <div><input type="button" value="登録" v-on:click="register()" /></div>
      <div><router-link to="/kasan/login">ログインへ</router-link></div>
    </article>
  </section>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";
import axios from "axios";

@Component
export default class AccountRegistrationPage extends Vue {

  eMail: string = "";
  password: string = "";
  name: string = "";

  addConfig: any = axios.create({
    headers: {
      'X-Requested-With': 'XMLHttpRequest',
      'Content-Type': 'application/json; charset=UTF-8'
    }
  })

  register(): void {
    let json: {EMail: string, Password: string, Name: string} = {
      EMail: this.eMail,
      Password: this.password,
      Name: this.name
    };
    this.addConfig.post("api/members", json)
    .then((result: any) => {
      alert("登録しました。");
      this.$router.push('/kasan/login');
    })
    .catch((result: any) => {
      alert("失敗しました。");
    })
  }

}
</script>

<style></style>
