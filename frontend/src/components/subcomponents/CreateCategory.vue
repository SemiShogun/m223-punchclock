<template>
  <div id="CreateCategory">
    <h1>Create Category</h1>
    <input type="text" placeholder="Category" v-model="category" />
    <br />
    <button @click="createValue">Generate</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit } from "vue-property-decorator";
import { Category } from "../../interfaces/Category";
import CategoryService from "../../services/CategoryService";

@Component
export default class CreateCategory extends Vue {
  private category: string;

  createValue() {
    const _category: Category = {
      name: this.category,
    };
    CategoryService.create(_category)
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
