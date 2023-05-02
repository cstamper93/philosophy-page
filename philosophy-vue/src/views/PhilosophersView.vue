<template>
  <div>
    <h1 class="title">Philosophers</h1>
    <div class="philosopher-container">
        <philosopher-content v-for="philosopher in listOfPhilosophers" 
        v-bind:key="philosopher.id"
        v-bind:philosopher = "philosopher" />
    </div>
  </div>
</template>

<script>
import BackendService from '@/services/BackendService';
import PhilosopherContent from '../components/PhilosopherContent.vue';
export default {
    components: { PhilosopherContent },
    name: 'philosophers-view',
    data() {
        return {
            listOfPhilosophers: []
        }
    },
    created() {
        BackendService.viewPhilosophersFromDb().then((response) => {
            this.listOfPhilosophers = response.data;
        });
        BackendService.loadIdeas();
        BackendService.loadSchools();
    }
};
</script>

<style>
  .philosopher-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
  }
  .title {
    margin-top: 2em;
    margin-bottom: 2em;
  }
</style>