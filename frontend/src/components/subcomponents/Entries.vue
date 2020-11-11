<template>
  <div id="Entries">
  </div>
</template>

<script>
import { Component, Vue } from "vue-property-decorator";

@Component
export default class Entries extends Vue {

  retrieveEntries() {
    EntryService.create({ checkIn, checkOut }).then(
      res => {
        console.log(res.data);

      }
    );
  }

  mounted() {
    this.retrieveEntries();
  }
  
}
</script>

<style>

</style>