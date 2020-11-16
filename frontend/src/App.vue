<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <div class="row">
        <a href="#" class="navbar-brand">PunchClock</a>
        <div class="navbar-nav mr-auto">
          <li class="nav-item">
            <div v-if="role === 'USER' || role === 'ADMIN'">
              <a href="/" class="nav-link">Entries</a>
            </div>
          </li>
          <li class="nav-item">
            <div v-if="role === 'ADMIN'">
              <a href="/room" class="nav-link">Rooms</a>
            </div>
          </li>
          <li class="nav-item">
            <div v-if="role === 'ADMIN'">
              <a href="/category" class="nav-link">Categories</a>
            </div>
          </li>
          <li class="nav-item">
            <div v-if="role === 'USER' || role === 'ADMIN'">
              <a href="/logout" class="nav-link">Logout</a>
            </div>
          </li>
          <li class="nav-item">
            <a href="/register" class="nav-link">Register</a>
          </li>
          <li class="nav-item">
            <div v-if="role != 'USER' && role != 'ADMIN'">
              <a href="/login" class="nav-link">Login</a>
            </div>
          </li>
        </div>
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
import AuthService from "./services/AuthService";

@Component({
  components: {
    PunchClock,
  },
})
export default class App extends Vue {
  private role = "";

  async checkRole() {
    let val = "";
    await AuthService.getRole()
      .then((res) => {
        console.log(res.data);
        val = res.data;
      })
      .catch((e) => {
        console.error(e);
      });
    if (val === "ADMIN") {
      this.role = "ADMIN";
    } else if (val === "USER") {
      this.role = "USER";
    } else {
      this.role = "";
    }
  }

  async mounted() {
    await this.checkRole();
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#navbarSupportedContent {
  text-align: center;
  display: block !important;
}

#navbarSupportedContent ul.navbar-nav {
  display: block !important;
}

#navbarSupportedContent ul.navbar-nav li {
  display: inline-block;
}
</style>
