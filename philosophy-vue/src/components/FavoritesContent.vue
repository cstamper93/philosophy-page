<template>
  <div class="fav-card" @mouseenter=" hover=true " @mouseleave="hover=false ">
    <h2 class="name">{{ favorite.name }}</h2>
    <img class="image" v-bind:src="favorite.photo">
    <button @click="removeFav(favorite)" class="remove-btn">Remove from Favorites</button>
    <!-- <p v-if="hover">Nationality: {{ favorite.nationality }}</p>
    <p v-if="hover">Era: {{ favorite.era }}</p> -->
  </div>
</template>

<script>
import BackendService from '@/services/BackendService';
export default {
    name: 'favorites-content',
    data() {
        return {
            hover: false,
        };
    },
    props: {
        favorite: Object
    },
    methods: {
        removeFav(favorite) {
            if(confirm("Are you sure you want to remove this philosopher from your favorites?")) {
                BackendService.removeFromFavs(favorite).then((response) => {
                    if(response.status === 200) {
                        alert("Successfully removed philosopher.");
                        location.reload();
                    }
                });
            }
        }
    }
};
</script>

<style>
  .image { 
    width: 10em; 
  }
  .fav-card {
    border: 2px solid black;
    border-radius: 10px;
    width: 15em;
    height: 24em;
    margin: 1em;
    padding: 0.5em;
  }
  .fav-card:hover {
    background-color: #eeeeee;
  }
  .remove-btn {
    margin: 0.75em;
  }
</style>