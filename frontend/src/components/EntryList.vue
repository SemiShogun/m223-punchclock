<template>
  <div id="EntryList">
    <h1>EntryList</h1>
    <button
      type="button"
      class="btn btn-primary"
      data-toggle="modal"
      data-target="#createEntry"
      @click="setCreateEntry()"
    >
      Create new Element
    </button>
    <ul class="list-group">
      <li
        class="list-group-item"
        :class="{ active: index == currentIndex }"
        v-for="(entry, index) in entries"
        :key="index"
      >
        checkIn: {{ entry.checkIn }} checkOut: {{ entry.checkOut }} category:
        {{ entry.category.name }} room: {{ entry.room.name }}
        <button
          type="button"
          class="btn btn-primary"
          @click="deleteEntry(entry.id)"
        >
          Delete
        </button>
        <button
          type="button"
          class="btn btn-primary"
          data-toggle="modal"
          data-target="#exampleModalLong"
          @click="setActiveEntry(entry, index)"
        >
          Update
        </button>
      </li>
    </ul>
    <div
      class="modal fade"
      id="exampleModalLong"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLongTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Edit Entry</h5>
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
                <option
                  v-for="(room, index) in rooms"
                  :key="index"
                  :value="room"
                >
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
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              Close
            </button>
            <button type="button" class="btn btn-primary" @click="updateValue">
              Save changes
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="entryClicked">
      <div
        class="modal fade"
        id="createEntry"
        tabindex="-1"
        role="dialog"
        aria-labelledby="createEntry"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <CreateEntry @addedEntry="retrieveEntries" />
        </div>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Entry } from "../interfaces/Entry";
import { Room } from "../interfaces/Room";
import { Category } from "../interfaces/Category";
import EntryService from "../services/EntryService";
import AuthService from "../services/AuthService";
import CreateEntry from "@/components/subcomponents/CreateEntry.vue";
import CreateRoom from "@/components/subcomponents/CreateRoom.vue";
import RoomService from "../services/RoomService";
import CreateCategory from "@/components/subcomponents/CreateCategory.vue";
import CategoryService from "../services/CategoryService";

@Component({
  components: {
    CreateEntry,
  },
})
export default class EntryList extends Vue {
  private entries: Array<Entry> = [];
  private currentEntry = null;
  private currentIndex = -1;
  private entryClicked = false;

  // Edit Form states
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

  async updateValue() {
    const _checkIn: string = this.formatDate(this.checkIn, this.checkInTime);
    const _checkOut: string = this.formatDate(this.checkOut, this.checkOutTime);
    const _room: Room = this.rooms.find((val) => val.name === this.room.name);
    const _category: Category = this.categories.find(
      (val) => val.name === this.category.name
    );
    const user = await AuthService.getCurrentUser().then((res) => {
      return res.data;
    });
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

    EntryService.update(this.currentEntry.id, entry)
      .then((res) => {
        console.log(res.data);
        this.retrieveEntries();
      })
      .catch((e) => {
        console.error(e);
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

  async refreshList() {
    this.retrieveEntries();
    this.currentEntry = null;
    this.currentIndex = -1;
  }

  setActiveEntry(entry: Entry, index: number) {
    this.currentEntry = entry;
    this.currentIndex = -1;
  }

  setCreateEntry() {
    this.entryClicked = true;
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

  mounted() {
    this.retrieveEntries();
    this.retrieveRooms();
    this.retrieveCategories();
  }
}
</script>

<style>
</style>