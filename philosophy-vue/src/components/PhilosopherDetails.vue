<template>
  <div class="details-box">
    <div class="img-div">
      <img class="philosopher-image" v-bind:src="philosopher.photo">
    </div>
    <div class="name-nat-era">
      <h1 class="name">{{ philosopher.name }}</h1>
      <h2>Nationality:</h2>
      <p><em>{{ philosopher.nationality }}</em></p>
      <h2>Era:</h2>
      <p><em>{{ philosopher.era }}</em></p>
    </div>
  </div>
</template>

<script>
export default {
    name: "philosopher-details",
    data() {
        return {}
    },
    props: {
      philosopher: Object
    },
};
</script>

<style>
  .details-box {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 1.5em;
  }
  .img-div {
    margin-right: 3em;
  }
  .name-nat-era {
    margin-left: 3em;
  }
</style>