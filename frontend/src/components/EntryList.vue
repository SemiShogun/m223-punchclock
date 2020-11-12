<template>
  <div id="EntryList">
    <CreateRoom @addedRoom="generateRoom" />
    <CreateCategory @addedCategory="generateCategory" />
    <CreateEntry
      :rooms="rooms"
      :categories="categories"
      @addedEntry="generateEntry"
    />
    <p v-for="(entry, index) in entries" :key="index">
      checkIn: {{ entry.checkIn }} checkOut: {{ entry.checkOut }} category:
      {{ entry.category.name }} room: {{ entry.room.name }}
      <button @click="deleteEntry(entry.id)">Delete</button>
    </p>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Entry } from "../models/Entry";
import { Room } from "../models/Room";
import { Category } from "../models/Category";
import EntryService from "../services/EntryService";
import CreateEntry from "@/components/subcomponents/CreateEntry.vue";
import CreateRoom from "@/components/subcomponents/CreateRoom.vue";
import RoomService from "../services/RoomService";
import CreateCategory from "@/components/subcomponents/CreateCategory.vue";
import CategoryService from "../services/CategoryService";

@Component({
  components: {
    CreateEntry,
    CreateRoom,
    CreateCategory,
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

  async generateRoom(room: Room) {
    await RoomService.create(room)
      .then((res) => {
        console.log(res.data);
        this.rooms.push(room);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  async generateCategory(category: Category) {
    await CategoryService.create(category)
      .then((res) => {
        console.log(res.data);
        this.categories.push(category);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  async retrieveRooms() {
    await RoomService.getAll()
      .then((res) => {
        console.log(res.data);
        this.rooms = res.data;
      })
      .catch((e) => {
        console.log(e);
      });
  }

  async retrieveCategories() {
    await CategoryService.getAll()
      .then((res) => {
        console.log(res.data);
        this.categories = res.data;
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

  async deleteEntry(entry: number) {
    await EntryService.delete(entry)
      .then((res) => {
        console.log(res.data);
        this.retrieveEntries();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  created() {
    this.retrieveEntries();
    this.retrieveRooms();
    this.retrieveCategories();
  }
}
</script>

<style>
</style>