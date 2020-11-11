<template>
  <div id="EntryList">
    <CreateEntry @addedEntry="generateEntry" />
    <ul>
      <li v-for="(entry, index) in entries" :key="index">
        checkIn: {{ entry.checkIn }} checkOut: {{ entry.checkOut }} category:
        {{ entry.category }} room: {{ entry.room }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Entry } from "../models/Entry";
import EntryService from "../services/EntryService";
import CreateEntry from "@/components/subcomponents/CreateEntry.vue";

@Component({
  components: {
    CreateEntry,
  },
})
export default class EntryList extends Vue {

  private entries: Array<Entry>;

  generateEntry(entry: Entry) {
    EntryService.create(entry)
      .then((res) => {
        this.retrieveEntries;
        console.log(res.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  retrieveEntries() {
    EntryService.getAll()
      .then(res => {
        this.entries = res.data;
        console.log(res.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  mounted() {
    this.retrieveEntries();
  }
}
</script>

<style>
</style>