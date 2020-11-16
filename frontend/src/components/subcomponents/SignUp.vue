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
import AuthService from "../../services/AuthService";

@Component
export default class SignUp extends Vue {
  private username = "";
  private password = "";

  async submit() {
    await AuthService.register(this.username, this.password);
    window.location.reload();
  }
}
</script>

<style>
</style>