<template>
  <div class="details-page">
    <philosopher-details :philosopher = "philosopher" />
    <div class="dividing-line"></div>
    <h2 class="schools-title">Schools of Thought</h2>
    <div class="schools-container">
        <schools-content v-for="school in schoolsList" 
        :key="school"
        :school="school"/>
    </div>
    <div class="dividing-line"></div>
    <div class="ideas-box">
        <h2 class="ideas-title">Key Ideas</h2>
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
            philosopher: null,
            ideasList: [],
            schoolsList: []
        }
    },
    created() {
        BackendService.fetchPhilosopherDeets(this.userChoice).then((response) => {
          if(response.status === 200) {
            console.log('Philosopher loaded here');
            this.philosopher = response.data;
          }
        });
        BackendService.fetchIdeasWithId(this.userChoice).then((response) => {
            if(response.status === 200) {
              console.log('Ideas loaded here.');
              this.ideasList = response.data;
            }
        });
        BackendService.fetchSchoolsWithId(this.userChoice).then((response) => {
            if(response.status === 200) {
              console.log('Schools loaded here.');
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
        }
    },  
}
</script>

<style>
  .details-page {
    padding-bottom: 4em;
  }
  .dividing-line {
    width: 85%;
    height: 0.2em;
    margin-left: auto;
    margin-right: auto;
    background-color: #d2691e;
  }
  .schools-title {
    margin-top: 1.5em;
  }
  .ideas-title {
    margin-top: 1.5em;
    margin-bottom: 0.5em;
  }
  .schools-container {
    margin-top: 2em;
    margin-bottom: 2em;
  }
</style>