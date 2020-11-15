<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <a href="#" class="navbar-brand">PunchClock</a>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <a href="/" class="nav-link">Entries</a>
        </li>
        <li class="nav-item">
          <a href="/entry/add" class="nav-link">Create Entry</a>
        </li>
        <li class="nav-item">
          <a href="/room/add" class="nav-link">Create Room</a>
        </li>
        <li class="nav-item">
          <a href="/category/add" class="nav-link">Create Category</a>
        </li>
        <li class="nav-item">
          <a href="/register" class="nav-link">Register</a>
        </li>
        <li class="nav-item">
          <a href="/login" class="nav-link">Login</a>
        </li>
        <li class="nav-item">
          <a href="/logout" class="nav-link">Logout</a>
        </li>
      </div>
    </nav>
    <div id="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import PunchClock from "@/components/PunchClock.vue";

@Component({
  components: {
    PunchClock,
  },
})
export default class App extends Vue {}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
