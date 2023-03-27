<template>
  <div>
    <h1>Displaying Philosophers</h1>
    <div class="philosopher-container">
        <philosopher-content v-for="philosopher in listOfPhilosophers" v-bind:key="philosopher.philosopherId"
        v-bind:philosopher= "philosopher" />
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
        BackendService.viewPhilosophersFromAPI().then((response) => {
            this.listOfPhilosophers = response.data;
            console.log(response.data);
        })
    }
};
</script>

<style>
  .philosopher-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
  }
</style>