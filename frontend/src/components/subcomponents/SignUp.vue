<template>
  <div id="SignUp">
    <h1>Sign Up</h1>
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
    <button type="submit" class="btn btn-primary" @click="submit()">
      Sign Up
    </button>
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