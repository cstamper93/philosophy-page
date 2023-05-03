<template>
  <div>
    <philosopher-details :userChoice = "userChoice" />
    <div class="dividing-line"></div>
        <h2>Schools of Thought:</h2>
        <schools-content v-for="school in schoolsList" 
        :key="school"
        :school="school"/>
    <div class="dividing-line"></div>
    <div class="ideas-box">
        <h1 class="ideas-title">Key Ideas</h1>
        <ideas-content v-for="idea in ideasList"
        v-bind:key="idea"
        v-bind:idea="idea" />
    </div>
  </div>
</template>

<script>
import BackendService from '@/services/BackendService';
import PhilosopherDetails from '../components/PhilosopherDetails.vue';
import IdeasContent from '../components/IdeasContent.vue';
import SchoolsContent from '../components/SchoolsContent.vue';
export default {
    components: { 
        PhilosopherDetails,
        IdeasContent,
        SchoolsContent
    },
    name: 'philosopher-details-view',
    data() {
        return {
            ideasList: [],
            schoolsList: []
        }
    },
    created() {
        BackendService.fetchIdeasWithId(this.userChoice).then((response) => {
            if(response.status === 200) {
                this.ideasList = response.data;
            }
        });
        BackendService.fetchSchoolsWithId(this.userChoice).then((response) => {
            if(response.status === 200) {
                this.schoolsList = response.data;
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
  .dividing-line {
    width: 85%;
    height: 0.2em;
    margin-left: auto;
    margin-right: auto;
    background-color: #d2691e;
  }
  .ideas-title {
    margin-bottom: 1.5em;
  }
</style>