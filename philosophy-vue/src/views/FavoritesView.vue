<template>
  <div>
    <h1>Favorites</h1>
    <div class="favorite-container">
      <favorites-content v-for="favorite in favoritesList" v-bind:key="favorite.id"
      v-bind:favorite = "favorite" />
    </div>
  </div>
</template>

<script>
import BackendService from '@/services/BackendService';
import FavoritesContent from '@/components/FavoritesContent.vue';
export default {
    components: { FavoritesContent },
    name: 'favorites-view',
    data() {
      return {
        favoritesList: []
      }
    },
    created() {
      BackendService.fetchFavorites().then((response) => {
        this.favoritesList = response.data;
        console.log(response.data);
      })
    }
};
</script>

<style>
  .favorite-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
  }
</style>