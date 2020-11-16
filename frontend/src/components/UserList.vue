<template>
  <div id="User">
    <h1>User</h1>
    <p>User ID: {{ currentUser.id }}</p>
    <p>Username: {{ currentUser.username }}</p>
    <br />
    <div v-if="role === 'ADMIN'">
      <h1>List of all users</h1>
      <ul class="list-group">
        <li class="list-group-item" v-for="(user, index) in users" :key="index">
          {{ index }}. username: {{ user }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { User } from "../interfaces/User";
import AuthService from "../services/AuthService";

@Component
export default class UserList extends Vue {
  private currentUser = {};
  private users: Array<User> = [];
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

  async retrieveCurrentUser() {
    await AuthService.getCurrentUser()
      .then((res) => {
        this.currentUser = res.data;
      })
      .catch((e) => {
        console.log(e);
      });
  }

  async retrieveAllUsers() {
    await AuthService.getAllUsers()
      .then((res) => {
        this.users = res.data;
      })
      .catch((e) => {
        console.log(e);
      });
  }

  mounted() {
    this.retrieveCurrentUser();
    this.retrieveAllUsers();
    this.checkRole();
  }
}
</script>

<style>
</style>