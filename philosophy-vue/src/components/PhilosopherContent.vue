<template>
  <div class="card" @mouseenter="hover=true" @mouseleave="hover=false">
    <h2 class="philosopher-name">{{ philosopher.name }}</h2>
    <img class="philosopher-image" v-bind:src="philosopher.photo">
    <p v-if="hover">Nationality: {{ philosopher.nationality }}</p>
    <p v-if="hover">Era: {{ philosopher.era }}</p>
  </div>
</template>

<script>
export default {
    name: "philosopher-content",
    data() {
        return {
            hover: false,
        };
    },
    props: {
        philosopher: Object
    }
}
</script>

<style>
  .philosopher-image { 
    width: 10em; 
  }
  .card {
    border: 2px solid black;
    border-radius: 10px;
    width: 15em;
    height: 22em;
    margin: 1em;
    padding: 0.5em;
  }
  .card:hover {
    background-color: #eeeeee;
    height: 27em;
  }
</style>