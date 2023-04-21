<template>
  <div>
    <h1>{{ philosopher.name }}</h1>
    <img class="philosopher-image" v-bind:src="philosopher.photo">
    <h2>Nationality: {{ philosopher.nationality }}</h2>
    <h2>Era: {{ philosopher.era }}</h2>
  </div>
</template>

<script>
import BackendService from '@/services/BackendService';
export default {
    name: "philosopher-details",
    data() {
        return {
          philosopher: null,
          ideasList: []
        }
    },
    props: {
      userChoice: Number
    },
    created() {
      BackendService.fetchPhilosopherDeets(this.userChoice).then((response) => {
        if(response.status === 200) {
          console.log(response.data);
          this.philosopher = response.data;
        }
      });
      // BackendService.fetchIdeasWithId(this.userChoice).then((response) => {
      //   if(response.status === 200) {
      //     console.log(response.data);
      //     this.ideasList = response.data;
      //   }
      // });
    }
};
</script>

<style>

</style>