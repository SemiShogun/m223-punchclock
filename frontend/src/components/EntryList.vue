<template>
  <div id="EntryList">
    <CreateEntry @addedEntry="generateEntry" />
    <ul>
      <li v-for="(entry, index) in entries" :key="index">
        checkIn: {{ entry.checkIn }} checkOut: {{ entry.checkOut }} category:
        {{ entry.category.name }} room: {{ entry.room.name }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Entry } from "../models/Entry";
import { Room } from "../models/Room";
import { Category } from "../models/Category";
import EntryService from "../services/EntryService";
import CreateEntry from "@/components/subcomponents/CreateEntry.vue";

@Component({
  components: {
    CreateEntry,
  },
})
export default class EntryList extends Vue {
  private entries: Array<Entry> = [];
  private rooms: Array<Room> = [];
  private categories: Array<Category> = [];

  async generateEntry(entry: Entry) {
    await EntryService.create(entry)
      .then((res) => {
        this.retrieveEntries();
        console.log(res.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  async retrieveEntries() {
    await EntryService.getAll()
      .then((res) => {
        console.log(res.data);
        this.entries = res.data;
      })
      .catch((e) => {
        console.log(e);
      });
  }

  created() {
    this.retrieveEntries();
  }
}
</script>

<style>
</style>