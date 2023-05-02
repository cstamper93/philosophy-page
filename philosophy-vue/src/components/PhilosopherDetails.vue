<template>
  <div class="details-box">
    <div class="img">
      <img class="philosopher-image" v-bind:src="philosopher.photo">
    </div>
    <div class="name-nat-era">
      <h1>{{ philosopher.name }}</h1>
      <h2>Nationality:</h2>
      <p>{{ philosopher.nationality }}</p>
      <h2>Era:</h2>
      <p>{{ philosopher.era }}</p>
    </div>
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
  .details-box {
    display: flex;
  }
  .img {
    margin-left: 2em;
    margin-right: 2em;
  }
  .name-nat-era {
    margin-left: 2em;
    margin-top: auto;
    margin-bottom: auto;
  }
</style>