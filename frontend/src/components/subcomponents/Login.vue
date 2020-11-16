<template>
  <div id="Login">
    <h1>Login</h1>
    <input type="text" placeholder="Username" v-model="username" />
    <input type="text" placeholder="Password" v-model="password" />
    <button @click="submit()">Login</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { User } from "../../interfaces/User";
import AuthService from "../../services/AuthService";

@Component
export default class Login extends Vue {
  private username = "";
  private password = "";

  async submit() {
    const _username = this.username;
    const _password = this.password;
    await AuthService.login(_username, _password);
    window.location.reload();
  }
}
</script>

<style>
</style>