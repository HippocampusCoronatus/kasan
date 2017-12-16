<template>
  <section>
    <h1>ログインテスト</h1>
    <article>
      <h1>ログイン情報入力</h1>
      <div>メールアドレス<input type="text" v-model="eMail" /></div>
      <div>パスワード<input type="password" v-model="password" /></div>
      <div><input type="button" value="ログイン" v-on:click="login()" /></div>
      <div><router-link to="/kasan/account_registration">新規登録へ</router-link></div>
    </article>
  </section>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";
import axios from "axios";

@Component
export default class LoginPage extends Vue {

  eMail: string = "";
  password: string = "";

  addConfig: any = axios.create({
    headers: {
      'X-Requested-With': 'XMLHttpRequest',
      'Content-Type': 'application/json; charset=UTF-8'
    }
  })

  login(): void {
    let json: {EMail: string, Password: string} = {
      EMail: this.eMail,
      Password: this.password
    };
    this.addConfig.post("api/authentication/login", json)
    .then((result: any) => {
      this.$router.push('/kasan/');
    })
    .catch((result: any) => {
      alert("ログインに失敗しました。");
    })
  }

}
</script>

<style></style>
