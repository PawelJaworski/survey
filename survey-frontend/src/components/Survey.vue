<template @surveysubmitted.native="surveySubmitted">
  <div class="hello"  >
    <div style="color:red">{{errors}}</div>
    <span>{{scoring}}</span>
    <Question v-for="question in survey.questionDefinitions"
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
      survey: {},
      scoring: '--',
      errors: ''
    }
  },
  mounted() {
    this.fetchData();
    this.updateScoring();
  },
  methods: {
    surveySubmitted() {
      this.errors = '';
      const answers = []
      for (const question in Vue.prototype.$store.getAnswers()) {
        for (const answer in Vue.prototype.$store.getAnswers()[question]) {
          answers.push({questionCode: question, answerCode: answer});
        }
      }

      this.$http.post(api + '/surveys/financialNeeds/javorex/answer', { answers })
              .then(this.fetchData)
              .catch(it => {
                  this.errors = it.response.data.errors.join('<\br>');
              })
              .then(this.updateScoring);
    },
    fetchData() {
      this.$http.get(api + '/surveys/actualFinancialNeeds/javorex')
              .then((result) => {
                this.survey = result.data;
              });
    },
    updateScoring() {
      this.$http.get(api + '/financialneeds/scorings/javorex')
              .then((result) => {
                const {data} = result;
                this.scoring = data
                        .map(it => it.type + ': ' + it.result)
                        .join(', ');
              });
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
