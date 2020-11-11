<template>
  <div id="CreateEntry">
    <h1>Create Entry</h1>
    <input type="date" placeholder="checkIn" v-model="checkIn" />
    <input type="date" placeholder="checkOut" v-model="checkOut" />
    <button @click="submit()">Access</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import EntryService from "../services/EntryService";

@Component
export default class CreateEntry extends Vue {

  checkIn = '';
  checkOut = '';

  retrieveEntries() {
    EntryService.create({ checkIn, checkOut }).then(
      res => {
        console.log(res.data);

      }
    );
  }

  mounted() {
    this.retrieveEntries();
  }
  
}
</script>

<style>

</style>