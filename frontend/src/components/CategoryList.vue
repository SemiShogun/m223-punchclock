<template>
  <div id="CategoryList">
    <h1>CategoryList</h1>
    <button
      type="button"
      class="btn btn-primary"
      data-toggle="modal"
      data-target="#createCategory"
      @click="setCreateCategory()"
    >
      Create new Element
    </button>
    <ul class="list-group">
      <li
        class="list-group-item"
        :class="{ active: index == currentIndex }"
        v-for="(category, index) in categories"
        :key="index"
      >
        category: {{ category.name }}
        <button
          type="button"
          class="btn btn-primary"
          @click="deleteCategory(category.id)"
        >
          Delete
        </button>
        <button
          type="button"
          class="btn btn-primary"
          data-toggle="modal"
          data-target="#exampleModalLong"
          @click="setActiveCategory(category, index)"
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
              Edit Category
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
    <div v-if="categoryClicked">
      <div
        class="modal fade"
        id="createCategory"
        tabindex="-1"
        role="dialog"
        aria-labelledby="createCategory"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <CreateCategory @addedCategory="refreshList" />
        </div>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Room } from "../interfaces/Room";
import { Category } from "../interfaces/Category";
import AuthService from "../services/AuthService";
import RoomService from "../services/RoomService";
import CreateCategory from "@/components/subcomponents/CreateCategory.vue";
import CategoryService from "../services/CategoryService";

@Component({
  components: {
    CreateCategory,
  },
})
export default class CategoryList extends Vue {
  private categories: Array<Category> = [];
  private currentCategory = null;
  private currentIndex = -1;
  private categoryClicked = false;

  // Edit Form states
  private category!: string;

  async updateValue() {
    const _category: Category = {
      name: this.category,
    };
    await CategoryService.update(this.currentCategory.id, _category)
      .then((res) => {
        console.log(res.data);
        this.retrieveCategories();
      })
      .catch((e) => {
        console.error(e);
      });
  }

  async retrieveCategories() {
    await CategoryService.getAll()
      .then((res) => {
        console.log(res.data);
        this.categories = res.data;
      })
      .catch((e) => {
        console.log(e);
      });
  }

  async refreshList() {
    this.retrieveCategories();
    this.currentCategory = null;
    this.currentIndex = -1;
  }

  setActiveCategory(category: Category, index: number) {
    this.currentCategory = category;
    this.currentIndex = -1;
  }

  setCreateCategory() {
    this.categoryClicked = true;
  }

  async deleteCategory(category: number) {
    await CategoryService.delete(category)
      .then((res) => {
        console.log(res.data);
        this.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  mounted() {
    this.retrieveCategories();
  }
}
</script>

<style>
</style>