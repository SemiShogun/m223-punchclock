<template>
  <div id="EntryList">
    <h1>EntryList</h1>
    <ul class="list-group">
      <li
        class="list-group-item"
        :class="{ active: index == currentIndex }"
        v-for="(entry, index) in entries"
        :key="index"
        @click="setActiveEntry(entry, index)"
      >
        checkIn: {{ entry.checkIn }} checkOut: {{ entry.checkOut }} category:
        {{ entry.category.name }} room: {{ entry.room.name }}
        <button @click="deleteEntry(entry.id)">Delete</button>
      </li>
    </ul>
    <div v-if="currentEntry">
      <h4>Entry</h4>
      {{ currentEntry.checkIn }}
      {{ currentEntry.checkOut }}
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Entry } from "../interfaces/Entry";
import { Room } from "../interfaces/Room";
import { Category } from "../interfaces/Category";
import EntryService from "../services/EntryService";
import CreateEntry from "@/components/subcomponents/CreateEntry.vue";
import CreateRoom from "@/components/subcomponents/CreateRoom.vue";
import RoomService from "../services/RoomService";
import CreateCategory from "@/components/subcomponents/CreateCategory.vue";
import CategoryService from "../services/CategoryService";

@Component
export default class EntryList extends Vue {
  private entries: Array<Entry> = [];
  private currentEntry = null;
  private currentIndex = -1;

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

  async refreshList() {
    this.retrieveEntries();
    this.currentEntry = null;
    this.currentIndex = -1;
  }

  setActiveEntry(entry: Entry, index: number) {
    this.currentEntry = entry;
    this.currentIndex = -1;
  }

  async deleteEntry(entry: number) {
    await EntryService.delete(entry)
      .then((res) => {
        console.log(res.data);
        this.refreshList();
      })
      .catch((e) => {
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