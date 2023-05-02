<template>
  <ul class="schools-list">
    <li>{{ school }}</li>
  </ul>
</template>

<script>
//import BackendService from '@/services/BackendService';

export default {
  name: "schools-content",
  props: { school: String },
  data() {
    return {
    }
  },
}
</script>

<style>
  .schools-list {
    display: flex;
    flex-direction: column;
    list-style-position: inside;
  }
</style>