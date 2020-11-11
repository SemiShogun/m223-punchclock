<template>
  <div id="Login">
    <h1>Login</h1>
    <input type="text" placeholder="Username" v-model="username" />
    <input type="text" placeholder="Password" v-model="password" />
    <button @click="submit()">Login</button>
  </div>
</template>

<script lang="ts">
import EntryService from '@/services/EntryService';
import { Component, Vue } from "vue-property-decorator";
import AuthService from "../services/AuthService";

@Component
export default class Login extends Vue {
  username = "";
  password = "";

  submit() {
    AuthService.login(this.username, this.password);
    this.$forceUpdate();
    console.log("updated");
    window.location.reload();
  }
}
</script>

<style>
</style>