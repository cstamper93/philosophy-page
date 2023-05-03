<template>
  <div class="details-box">
    <div class="img-div">
      <img class="philosopher-image" v-bind:src="philosopher.photo">
    </div>
    <div class="name-nat-era">
      <h1 class="name">{{ philosopher.name }}</h1>
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
    }
};
</script>

<style>
  .details-box {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .img-div {
    margin-right: 2em;
  }
  .name-nat-era {
    margin-left: 2em;
  }
</style>