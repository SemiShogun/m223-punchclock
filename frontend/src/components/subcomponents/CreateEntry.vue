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

@Component
export default class CreateEntry extends Vue {
  private checkIn: Date;
  private checkOut: Date;
  private checkInTime: string;
  private checkOutTime: string;

  formatDate(date: Date, time: string): string {
    const _date = new Date(date);
    _date.setHours(parseInt(time.split(":")[0]));
    _date.setMinutes(parseInt(time.split(":")[1]));
    return _date.toISOString();
  }

  createValue() {
    const _checkIn = this.formatDate(this.checkIn, this.checkInTime);
    const _checkOut = this.formatDate(this.checkOut, this.checkOutTime);
    const entry: Entry = {
      checkIn: _checkIn,
      checkOut: _checkOut
    };

    this.generateEntry(entry);
  }

  @Emit("addedEntry") generateEntry(entry: Entry) {
    console.log(`Adding Entry: ${entry.checkIn} + ${entry.checkOut}`);
  }
}
</script>

<style>
</style>