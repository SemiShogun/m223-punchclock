<template>
  <div id="EntryList">
    <ul>
      <li v-for="(entry, index) in entries" :key="index">
        checkIn: {{ entry.checkIn }} checkOut: {{ entry.checkOut }} category: {{ entry.category }} room: {{ entry.room }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import EntryService from "../services/EntryService";

@Component
export default class EntryList extends Vue {
  private entries: any[] = [];

  retrieveEntries() {
    EntryService
      .getAll()
      .then(res => {
        this.entries = res.data;
        console.log(res.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  mounted() {
    this.retrieveEntries();
  }
}
</script>

<style>
</style>