<template>
  <div id="Login">
    <h1>Log Out</h1>
    <button type="submit" class="btn btn-primary" @click="submit()">Log Out</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import AuthService from "../../services/AuthService";

@Component
export default class SignOut extends Vue {
  submit() {
    AuthService.logout();
    window.location.reload();
  }
}
</script>

<style>
</style>