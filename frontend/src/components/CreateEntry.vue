<template>
  <div id="CreateEntry">
    <h1>Create Entry</h1>
    <input type="text" placeholder="Username" v-model="username" />
    <input type="text" placeholder="Password" v-model="password" />
    <button @click="submit()">Access</button>
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";

@Component
export default class CreateEntry extends Vue {

  
}
</script>

<style>

</style>