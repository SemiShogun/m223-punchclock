<template>
  <div id="CreateRoom">
    <h1>Create Room</h1>
    <input type="text" placeholder="Room" v-model="room" />
    <br />
    <button @click="createValue">Generate</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Room } from "../../models/Room";

@Component
export default class CreateRoom extends Vue {
  private room: string;

  createValue() {
    const _room: Room = {
      name: this.room,
    };
    this.generateRoom(_room);
  }

  @Emit("addedRoom") generateRoom(room: Room) {
    console.log(`Added Room: ${room.name}`);
  }
}
</script>

<style>
</style>
