<template>
  <div class="main">
    <div>かあさん貸借システム</div>
    <div><h3 v-cloak>{{name}}</h3></div>
    <article>
      <section>
        <h4>【あなたのかしかり】</h4>
        <p v-for="account in accountList" v-cloak>
          {{account.PartnerMemberID}} : {{account.LoanAmount}}
        </p>
      </section>
    </article>
    <form accept-charset="UTF-8" action="" method="">
      <div class="form-body">
        <label for="input_name">名前を入力してください。</label>
        <input type="text" class="input-block" name="input_name" id="input_name" placeholder="名前を入力してください。" autocomplete="name" v-model="inputName" />
        <label for="amount_of_maney">金額を入力してください。</label>
        <input type="text" class="input-block" name="amount_of_maney" id="amount_of_maney" placeholder="金額を入力してください。" v-model="amountOfManey" />
        <div>
          <input type="button" class="btn btn-primary btn-block" value="確認" v-on:click="a(this.form)" />
        </div>
      </div>
    </form>
    <div><a v-on:click="logout()">ログアウト</a></div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from "vue-property-decorator";
import axios from "axios";

@Component
export default class MainPage extends Vue {

  name: string = "";
  accountList: Array<string> = [];
  inputName: string = "";
  amountOfManey: string = "";

  addConfig = axios.create({
    headers: {
      'X-Requested-With': 'XMLHttpRequest',
      'Content-Type': 'application/json; charset=UTF-8'
    }
  })

  logout(): void {
    this.addConfig.post("api/authentication/logout")
    .then(() => {
      location.href = "login_test.html";
    })
    .catch((e) => {
      alert("ログアウトに失敗しました。");
    })
  }

  a(frm: any): any {
      if (!this.inputName) {
          alert("名前を入力してください。");
          return;
      }
      if (!this.amountOfManey) {
          alert("金額を入力してください。");
          return;
      }
      if (this.amountOfManey.match(/[^0-9]+/)) {
          alert("金額は半角数字でオナシャス");
          return;
      }
      alert('もらった！');
      frm.submit();
  }

  created() {
    axios.all([
      this.addConfig.get("api/members/me"),
      this.addConfig.get("api/accounts")
    ])
    .then( axios.spread( (me: any, accounts: any) => {
      if (me.status === 204 || accounts.status === 204) {
        alert("データがありませんでした。");
      }
      this.name = me.data.Name + "さんのページ";
      this.accountList = accounts.data;
    }))
    .catch((e: any) => {
      alert("初期化通信に失敗しました。");
      location.href = "login_test.html";
    })
  }

}

</script>

<style scoped>
[v-cloak] {
  display: none;
}

.main {
  width: 30%;
  height: 60%;
  margin: 100px auto;
}

.form-body {
  width: 80%;
  padding: 20px;
  font-size: 14px;
  background-color: #fff;
  border: 1px solid #d8dee2;
  border-top: 0;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  -ms-border-radius: 5px;
}

.input-block {
  display: block;
  width: 80%;
}

</style>
