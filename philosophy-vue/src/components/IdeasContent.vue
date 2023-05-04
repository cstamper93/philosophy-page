<template>
  <div class="ideas-box">
    <h1 class="dot-separator">. . .</h1>
    <p>"{{ idea }}"</p>
  </div>
</template>

<script>
export default {
    name: "ideas-content",
    props: { idea: String },
    data() {
        return {
            ideasList: []
        }
    }
}
</script>

<style>
  .ideas-box {
    margin-left: 7em;
    margin-right: 7em;
  }
  .dot-separator {
    color: #d2691e;
  }
</style>