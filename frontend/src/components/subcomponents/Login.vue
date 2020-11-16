<template>
  <div id="Login">
    <h1>Login</h1>
    <label for="username">Username</label>
    <input
      type="text"
      class="form-control"
      id="username"
      placeholder="Username"
      v-model="username"
    />
    <label for="Pass">Password</label>
    <input
      type="password"
      class="form-control"
      id="Pass"
      placeholder="Password"
      v-model="password"
    />
    <button type="submit" class="btn btn-primary" @click="submit()">Login</button>
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