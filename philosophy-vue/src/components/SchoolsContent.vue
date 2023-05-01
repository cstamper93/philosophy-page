<template>
  <div>
    <p>{{ school }}</p>
  </div>
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

</style>