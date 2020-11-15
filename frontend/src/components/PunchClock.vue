<template>
  <div id="PunchClock">
    <h1>PunchClock Application</h1>
    <Login />
    <SignUp />
    <SignOut />
    <EntryList />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { Entry } from "../interfaces/Entry";
import EntryService from "../services/EntryService";
import Login from "@/components/subcomponents/Login.vue";
import EntryList from "@/components/EntryList.vue";
import SignUp from "@/components/subcomponents/SignUp.vue";
import SignOut from "@/components/subcomponents/SignOut.vue";

@Component({
  components: {
    Login,
    EntryList,
    SignUp,
    SignOut,
  },
})
export default class PunchClock extends Vue {}
</script>

<style>
</style>