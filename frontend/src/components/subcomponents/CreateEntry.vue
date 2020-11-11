<template>
  <div id="CreateEntry">
    <h1>Create Entry</h1>
    <input type="date" placeholder="checkIn" v-model="checkIn" />
    <input type="time" v-model="checkInTime" />
    <input type="date" placeholder="checkOut" v-model="checkOut" />
    <input type="time" v-model="checkOutTime" />
    <button @click="createValue">Access</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Entry } from "../../models/Entry";
import * as moment from "moment";

@Component
export default class CreateEntry extends Vue {

  checkIn: Date;
  checkOut: Date;
  checkInTime: Date;
  checkOutTime: Date;

  formatDate(date: Date, time: Date): Date {
    var _checkIn = this.checkIn.toDateString;
    var _checkOut = this.checkOut.toDateString;
    return moment.format();
  }

  createValue() {
    var firstCheck = this.formatDate(this.checkIn, this.checkInTime);
    const entry: Entry = {
      checkIn: new Date(`${this.checkIn}T${this.checkInTime}`).toISOString(),
      checkOut: this.checkOut
    }

    this.generateEntry(entry);
  }

  @Emit("addedEntry") generateEntry(entry: Entry) {
    console.log(`Adding Entry: ${entry.checkIn} + ${entry.checkOut}`)
  }
  
}
</script>

<style>

</style>