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
import { Room } from "../../interfaces/Room";
import RoomService from "../../services/RoomService";

@Component
export default class CreateRoom extends Vue {
  private room: string;

  createValue() {
    const _room: Room = {
      name: this.room,
    };
    RoomService.create(_room)
      .then((res) => {
        console.log(res.data);
      })
      .catch((e) => {
        console.error(e);
      });
  }
}
</script>

<style>
</style>
