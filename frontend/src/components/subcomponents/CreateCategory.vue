<template>
  <div id="CreateCategory">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Create Category</h5>
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
          <label for="category">Category</label>
          <input
            type="text"
            class="form-control"
            id="category"
            v-model="category"
          />
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
import { Category } from "../../interfaces/Category";
import CategoryService from "../../services/CategoryService";

@Component
export default class CreateCategory extends Vue {
  private category: string;

  async createValue() {
    const _category: Category = {
      name: this.category,
    };
    await CategoryService.create(_category)
      .then((res) => {
        console.log(res.data);
        this.generateCategory();
      })
      .catch((e) => {
        console.error(e);
      });
  }

  @Emit("addedCategory") generateCategory() {
    console.log(`Added Category`);
  }
}
</script>

<style>
</style>
