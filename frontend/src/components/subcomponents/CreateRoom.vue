<template>
  <div id="CreateRoom">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Create Room</h5>
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
          <label for="room">Room</label>
          <input type="text" class="form-control" id="room" v-model="room" />
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
        this.generateRoom();
      })
      .catch((e) => {
        console.error(e);
      });
  }
 
  @Emit("addedRoom") generateRoom() {
    console.log(`Added Room`);
  }
}
</script>

<style>
</style>
