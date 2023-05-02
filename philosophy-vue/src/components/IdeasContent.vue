<template>
  <div class="ideas-box">
    <p>"{{ idea }}"</p>
  </div>
</template>

<script>
import BackendService from '@/services/BackendService';
export default {
    name: "ideas-content",
    props: { idea: String },
    data() {
        return {
            ideasList: []
        }
    },
    created() {
        BackendService.fetchIdeasWithId(this.userChoice).then((response) => {
        if(response.status === 200) {
          console.log(response.data);
          this.ideasList = response.data;
        }
      });
    }
}
</script>

<style>
  .ideas-box {
    margin-left: 7em;
    margin-right: 7em;
  }
</style>