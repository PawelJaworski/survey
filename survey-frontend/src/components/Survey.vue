<template @surveysubmitted.native="surveySubmitted">
  <div class="hello"  >
    <Question
            v-for="question in survey.questionDefinitions"
              v-bind:key="question.questionCode"
              v-bind:question-code="question.questionCode"
              v-bind:survey="survey"></Question>
    <input type="button" v-on:click="surveySubmitted()" value="Submit">
  </div>
</template>

<script>
import Vue from "vue";
import Question from "./financialneeds/Question";
import axios from "axios";
import {SurveyStore} from "../store/SurveyStore";

Vue.prototype.$http = axios
Vue.prototype.$store = SurveyStore

const api = 'http://localhost:9999';

export default {
  name: 'Survey',
  components: {Question},
  data: function () {
    return {
      survey: {}
    }
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    surveySubmitted() {
      console.log('submitted... ' + JSON.stringify(Vue.prototype.$store.getAnswers()));
      const answers = []
      for (const question in Vue.prototype.$store.getAnswers()) {
        for (const answer in Vue.prototype.$store.getAnswers()[question]) {
          answers.push({questionCode: question, answerCode: answer});
        }
      }

      this.$http.post(api + '/surveys/FINANCIAL_NEEDS/UNIT_LINKED.v1/javorex/answer', {
        answers: answers
      }).then(this.fetchData);
    },
    fetchData() {
      this.$http.get(api + '/surveys/FINANCIAL_NEEDS/UNIT_LINKED.v1/javorex')
              .then((result) => {
                this.survey = result.data;
              });
    }
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
