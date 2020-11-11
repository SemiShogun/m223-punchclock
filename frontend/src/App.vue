<template>
  <div id="app">
    <h1>PunchClock</h1>
    <Login />
    <SignUp />
    <EntryList />
    <SignOut />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Login from "@/components/Login.vue";
import EntryList from "@/components/EntryList.vue";
import SignUp from "@/components/SignUp.vue";
import SignOut from "@/components/SignOut.vue";

@Component({
  components: {
    Login,
    EntryList,
    SignUp,
    SignOut,
  }
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
  margin-top: 60px;
}
</style>
