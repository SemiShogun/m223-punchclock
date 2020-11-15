<template>
  <div id="SignUp">
    <h1>Sign Up</h1>
    <input type="text" placeholder="Username" v-model="username" />
    <input type="text" placeholder="Password" v-model="password" />
    <button @click="submit()">Register</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { User } from "../../interfaces/User";
import AuthService from "../../services/AuthService";

@Component
export default class Login extends Vue {
  username = "";
  password = "";

  submit() {
    const user: User = {
      username: this.username,
      password: this.password
    }
    AuthService.register(user);
    window.location.reload();
  }
}
</script>

<style>
</style>