<template>
  <div v-if="currentEntry" class="edit-form">
    <h4>Entry</h4>
    <form>
      <div class="form-group">
        <label for="checkIn">CheckIn</label>
        <input
          type="date"
          class="form-control"
          id="checkIn"
          placeholder="checkIn"
          v-model="currentEntry.checkIn"
        />
      </div>
      <div class="form-group">
        <label for="checkInTime">checkInTime</label>
        <input
          type="time"
          class="form-control"
          id="checkInTime"
          placeholder="checkInTime"
          v-model="currentEntry.checkInTime"
        />
      </div>
      <div class="form-group">
        <label for="checkOut">checkOut</label>
        <input
          type="date"
          class="form-control"
          id="checkOut"
          placeholder="checkOut"
          v-model="currentEntry.checkOut"
        />
      </div>
      <div class="form-group">
        <label for="checkOutTime">checkOutTime</label>
        <input
          type="time"
          class="form-control"
          id="checkOutTime"
          placeholder="checkOutTime"
          v-model="currentEntry.checkOutTime"
        />
      </div>
      <div class="form-group">
        <label>Room</label>
        {{ currentEntry.room }}
      </div>
      <div class="form-group">
        <label>Category</label>
        {{ currentEntry.category }}
      </div>
    </form>

    <button class="badge badge-danger mr-2" @click="deleteEntry">Delete</button>

    <button type="submit" class="badge badge-success" @click="updateEntry">
      Update
    </button>
  </div>
  <div v-else>
    <p>Please click on an entry</p>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import EntryService from "../../services/EntryService";
import { Entry } from "../../interfaces/Entry";

@Component
export default class EntryDetails extends Vue {
    private currentEntry = null;
    private message: string = "";

    async getEntry(id: number) {
      EntryService.getById(id)
      .then(res => {
        this.currentEntry = res.data;
        console.log(res.data);
      })
      .catch(e => {
        console.log(e);
      })
    }

    updateEntry() {
      const id = this.currentEntry.id;
      EntryService.update(id, this.currentEntry)
      .then(res => {
        console.log(res.data);
        this.message = "Entry updated successfully!";
      })
      .catch(e => {
        console.log(e);
      })
    }

    deleteEntry() {
      EntryService.delete(this.currentEntry.id)
      .then(res => {
        console.log(res.data);
      })
      .catch(e => {
        console.log(e);
      })
    }

    mounted() {
      this.message = "";
      this.getEntry(this.)
    }
}
</script>

<style>
</style>