<template>
  <div id="CreateEntry">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Create Entry</h5>
        <button
          type="button"
          class="close"
          data-dismiss="modal"
          aria-label="Close"
        >
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label for="checkIn">CheckIn</label>
          <input
            type="date"
            class="form-control"
            id="checkIn"
            v-model="checkIn"
          />
          <label for="checkInTime">CheckInTime</label>
          <input
            type="time"
            class="form-control"
            id="checkInTime"
            v-model="checkInTime"
          />
          <label for="checkOut">checkOut</label>
          <input
            type="date"
            class="form-control"
            id="checkOut"
            v-model="checkOut"
          />
          <label for="checkOutTime">checkOut</label>
          <input
            type="time"
            class="form-control"
            id="checkOutTime"
            v-model="checkOutTime"
          />
          <label for="rooms">Rooms</label>
          <select v-model="room" class="form-control" id="rooms">
            <option v-for="(room, index) in rooms" :key="index" :value="room">
              {{ room.name }}
            </option>
          </select>
          <label for="categories">Categories</label>
          <select v-model="category" class="form-control" id="categories">
            <option disabled selected>Categories</option>
            <option
              v-for="(category, index) in categories"
              :key="index"
              :value="category"
            >
              {{ category.name }}
            </option>
          </select>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">
          Close
        </button>
        <button type="button" class="btn btn-primary" @click="createValue">
          Save changes
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Entry } from "../../interfaces/Entry";
import { Room } from "../../interfaces/Room";
import { Category } from "../../interfaces/Category";
import { User } from "../../interfaces/User";
import RoomService from "../../services/RoomService";
import CategoryService from "../../services/CategoryService";
import EntryService from "../../services/EntryService";
import UserService from "../../services/AuthService";
import AuthService from "../../services/AuthService";

@Component
export default class CreateEntry extends Vue {
  private checkIn: Date;
  private checkOut: Date;
  private checkInTime: string;
  private checkOutTime: string;

  private rooms: Array<Room> = [];
  private room: Room;

  private categories: Array<Category> = [];
  private category: Category;

  formatDate(date: Date, time: string): string {
    const _date = new Date(date);
    _date.setHours(parseInt(time.split(":")[0]));
    _date.setMinutes(parseInt(time.split(":")[1]));
    return _date.toISOString();
  }

  async createValue() {
    const _checkIn: string = this.formatDate(this.checkIn, this.checkInTime);
    const _checkOut: string = this.formatDate(this.checkOut, this.checkOutTime);
    const _room: Room = this.rooms.find((val) => val.name === this.room.name);
    const _category: Category = this.categories.find(
      (val) => val.name === this.category.name
    );
    const user = await AuthService.getCurrentUser().then((res) => {
      return res.data;
    });
    console.log(`User ${user}`);
    const _user: User = {
      username: user,
    };
    const entry: Entry = {
      checkIn: _checkIn,
      checkOut: _checkOut,
      room: _room,
      category: _category,
      user: _user,
    };
    console.log(entry);

    EntryService.create(entry)
      .then((res) => {
        console.log(res.data);
        this.generateEntry();
      })
      .catch((e) => {
        console.error(e);
      });
  }

  async retrieveRooms() {
    await RoomService.getAll()
      .then((res) => {
        console.log(res.data);
        this.rooms = res.data;
      })
      .catch((e) => {
        console.error(e);
      });
  }

  async retrieveCategories() {
    await CategoryService.getAll()
      .then((res) => {
        console.log(res.data);
        this.categories = res.data;
      })
      .catch((e) => {
        console.error(e);
      });
  }

  @Emit("addedEntry") generateEntry() {
    console.log(`Added Entry`);
  }

  mounted() {
    this.retrieveRooms();
    this.retrieveCategories();
  }
}
</script>

<style>
</style>