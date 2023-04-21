<template>
  <router-link v-bind:to="{name: 'Philosopher-Details-View'}">
  <div class="card" @mouseenter="hover=true" 
  @mouseleave="hover=false"
  v-on:click="saveUserChoice(philosopher.id)">
    <h2 class="philosopher-name">{{ philosopher.name }}</h2>
    <img class="philosopher-image" v-bind:src="philosopher.photo">
    <button v-if="philosopher.favorited == false" class="fav-btn" 
    v-on:click.prevent="addToFavorites(philosopher)">Add to Favorites
    </button>
    <!-- <p v-if="hover">Nationality: {{ philosopher.nationality }}</p>
    <p v-if="hover">Era: {{ philosopher.era }}</p> -->
  </div>
  </router-link>
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
            BackendService.addToFavs(philosopher).then((response) => {
                if(response.status === 200) {
                    alert("Philosopher added!");
                    location.reload;
                }
            });
            }
        },
        saveUserChoice(id) {
          try {
            localStorage.setItem('userChoice', id);
            console.log('User choice saved to local storage');
          } catch (error) {
            console.error('Error saving user choice to local storage: ', error);
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
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border: 2px solid black;
    border-radius: 10px;
    width: 15em;
    height: 24em;
    margin: 1em;
    padding: 0.5em;
  }
  .card:hover {
    background-color: #eeeeee;
  }
  .fav-btn {
    margin: 0.75em;
  }
</style>