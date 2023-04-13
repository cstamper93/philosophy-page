<template>
  <div class="card" @mouseenter="hover=true" @mouseleave="hover=false">
    <h2 class="philosopher-name">{{ philosopher.name }}</h2>
    <img class="philosopher-image" v-bind:src="philosopher.photo">
    <button class="fav-btn" v-on:click.prevent="addToFavorites(philosopher)">Add to Favorites</button>
    <p v-if="hover">Nationality: {{ philosopher.nationality }}</p>
    <p v-if="hover">Era: {{ philosopher.era }}</p>
  </div>
</template>

<script>
import BackendService from '@/services/BackendService';
export default {
    name: "philosopher-content",
    data() {
        return {
            hover: false,
        };
    },
    props: {
        philosopher: Object
    },
    methods: {
        addToFavorites(philosopher) {
            if(confirm("Add philosopher to your favorites?")) {
            BackendService.addPhilosopherToFavorites(philosopher).then((response) => {
                if(response.status === 201) {
                    alert("Philosopher added!");
                }
            });
            }
        }
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
    height: 24em;
    margin: 1em;
    padding: 0.5em;
  }
  .card:hover {
    background-color: #eeeeee;
    height: 29em;
  }
  .fav-btn {
    margin: 0.75em;
  }
</style>