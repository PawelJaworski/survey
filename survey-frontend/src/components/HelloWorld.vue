<template>
  <div class="hello">
    <Question
            v-for="question in survey.questionDefinitions"
              v-bind:key="question.questionCode"
              v-bind:question-code="question.questionCode"
              v-bind:survey="survey"></Question>
  </div>
</template>

<script>
import Question from "./financialneeds/Question";
export default {
  name: 'HelloWorld',
  components: {Question},
  data: function () {
    return {
      survey: {}
    }
  },
  mounted() {
    this.$http.get('http://localhost:9999/surveys/FINANCIAL_NEEDS/UNIT_LINKED.v1/javorex')
      .then((result) => {
        console.log('result: ' + JSON.stringify(result));
        this.survey = result.data;
      })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
