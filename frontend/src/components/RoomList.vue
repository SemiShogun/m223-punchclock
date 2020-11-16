<template>
  <div id="RoomList">
    <h1>RoomList</h1>
    <button
      type="button"
      class="btn btn-primary"
      data-toggle="modal"
      data-target="#createRoom"
      @click="setCreateRoom()"
    >
      Create new Element
    </button>
    <ul class="list-group">
      <li
        class="list-group-item"
        :class="{ active: index == currentIndex }"
        v-for="(room, index) in rooms"
        :key="index"
      >
        room: {{ room.name }}
        <button
          type="button"
          class="btn btn-primary"
          @click="deleteRoom(room.id)"
        >
          Delete
        </button>
        <button
          type="button"
          class="btn btn-primary"
          data-toggle="modal"
          data-target="#exampleModalLong"
          @click="setActiveRoom(room, index)"
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
            <h5 class="modal-title" id="exampleModalLongTitle">
              Edit Room
            </h5>
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
              <input
                type="text"
                class="form-control"
                id="room"
                v-model="room"
              />
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
    <div v-if="roomClicked">
      <div
        class="modal fade"
        id="createRoom"
        tabindex="-1"
        role="dialog"
        aria-labelledby="createRoom"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <CreateRoom @addedRoom="refreshList" />
        </div>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Room } from "../interfaces/Room";
import RoomService from "../services/RoomService";
import CreateRoom from "@/components/subcomponents/CreateRoom.vue";

@Component({
  components: {
    CreateRoom,
  },
})
export default class RoomList extends Vue {
  private rooms: Array<Room> = [];
  private currentRoom = null;
  private currentIndex = -1;
  private roomClicked = false;

  // Edit Form states
  private room: string;

  async updateValue() {
    const _room: Room = {
      name: this.room,
    };
    await RoomService.update(this.currentRoom.id, _room)
      .then((res) => {
        console.log(res.data);
        this.retrieveRooms();
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
        console.log(e);
      });
  }

  async refreshList() {
    this.retrieveRooms();
    this.currentRoom = null;
    this.currentIndex = -1;
  }

  setActiveRoom(room: Room, index: number) {
    this.currentRoom = room;
    this.currentIndex = -1;
  }

  setCreateRoom() {
    this.roomClicked = true;
  }

  async deleteRoom(room: number) {
    await RoomService.delete(room)
      .then((res) => {
        console.log(res.data);
        this.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  mounted() {
    this.retrieveRooms();
  }
}
</script>

<style>
</style>