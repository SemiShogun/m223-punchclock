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
import { Category } from "../../models/Category";

@Component
export default class CreateCategory extends Vue {
  private category: string;

  createValue() {
    const _category: Category = {
      name: this.category,
    };
    this.generateCategory(_category);
  }

  @Emit("addedCategory") generateCategory(category: Category) {
    console.log(`Added Category: ${category.name}`);
  }
}
</script>

<style>
</style>
