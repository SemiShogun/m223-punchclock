<template>
  <div id="CreateEntry">
    <h1>Create Entry</h1>
    <input type="date" placeholder="checkIn" v-model="checkIn" />
    <input type="time" v-model="checkInTime" />
    <br />
    <input type="date" placeholder="checkOut" v-model="checkOut" />
    <input type="time" v-model="checkOutTime" />
    <br />
    <select v-model="room">
      <option disabled selected>Rooms</option>
      <option v-for="(room, index) in rooms" :key="index" :value="room">
        {{ room.name }}
      </option>
    </select>
    <br />
    <select v-model="category">
      <option disabled selected>Categories</option>
      <option
        v-for="(category, index) in categories"
        :key="index"
        :value="category"
      >
        {{ category.name }}
      </option>
    </select>
    <br />
    <button @click="createValue">Generate</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Entry } from "../../models/Entry";
import { Room } from "../../models/Room";
import { Category } from "../../models/Category";
import RoomService from "../../services/RoomService";
import CategoryService from "../../services/CategoryService";
import EntryService from "../../services/EntryService";

@Component
export default class CreateEntry extends Vue {
  private checkIn: Date;
  private checkOut: Date;
  private checkInTime: string;
  private checkOutTime: string;

  @Prop({ default: [] })
  private rooms: Array<Room> = [];
  private room: Room;

  @Prop({ default: [] })
  private categories: Array<Category> = [];
  private category: Category;

  formatDate(date: Date, time: string): string {
    const _date = new Date(date);
    _date.setHours(parseInt(time.split(":")[0]));
    _date.setMinutes(parseInt(time.split(":")[1]));
    return _date.toISOString();
  }

  createValue() {
    console.log(this.room);
    const _checkIn: string = this.formatDate(this.checkIn, this.checkInTime);
    const _checkOut: string = this.formatDate(this.checkOut, this.checkOutTime);
    const _room: Room = this.rooms.find((val) => val.name === this.room.name);
    const _category: Category = this.categories.find(
      (val) => val.name === this.category.name
    );
    console.log(_room);
    console.log(_category);
    const entry: Entry = {
      checkIn: _checkIn,
      checkOut: _checkOut,
      room: _room,
      category: _category,
    };

    this.generateEntry(entry);
  }

  @Emit("addedEntry") generateEntry(entry: Entry) {
    console.log(
      `Adding Entry: ${entry.checkIn} ${entry.checkOut} ${entry.room} ${entry.category}`
    );
  }
}
</script>

<style>
</style>