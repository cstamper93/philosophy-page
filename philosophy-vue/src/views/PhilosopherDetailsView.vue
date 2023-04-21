<template>
  <div>
    <philosopher-details :userChoice = "userChoice" />
    <h1>Ideas</h1>
    <ideas-content v-for="idea in ideasList"
    v-bind:key="idea"
    v-bind:idea="idea" />
  </div>
</template>

<script>
import BackendService from '@/services/BackendService';
import PhilosopherDetails from '../components/PhilosopherDetails.vue';
import IdeasContent from '../components/IdeasContent.vue';
export default {
    components: { 
        PhilosopherDetails,
        IdeasContent
    },
    name: 'philosopher-details-view',
    data() {
        return {
            ideasList: []
        }
    },
    created() {
        BackendService.fetchIdeasWithId(this.userChoice).then((response) => {
            if(response.status === 200) {
                this.ideasList = response.data;
            }
        });
    },
    computed: {
        userChoice() {
            try{
                const userChoice = localStorage.getItem('userChoice');
                console.log('User choice retrieved from local storage: ', userChoice);
                return Number(userChoice);
            } catch(error) {
                console.error('Error retrieving user selection from local storage :', error);
                return null;
            }
        },
        // philosopherDetails() {
        //     return BackendService.fetchPhilosopherDeets(this.userChoice);
        // },
        // ideas() {
        //     return BackendService.fetchIdeasWithId(this.userChoice);
        // },
    },  
}
</script>

<style>

</style>