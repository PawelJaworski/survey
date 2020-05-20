<template>
    <div v-if="survey" class="question">
        <div>{{questionCode}}</div>
        <div v-for="possibleAnswer in possibleAnswers"
             v-bind:key="possibleAnswer.answerCode">
            <label :for="questionCode + '__' + possibleAnswer">{{possibleAnswer}}</label>
            <input type="radio"
                   :id="questionCode + '__' + possibleAnswer"
                   :name="questionCode"
                   :value="possibleAnswer"
                   :checked="possibleAnswer === answerCode"/>
            <span v-if="possibleAnswer === answerCode && answerText">{{answerText}}</span>
        </div>

    </div>
</template>

<script>
    export default {
        name: "Question",
        props: {
            questionCode: {
                type: String,
                required: true
            },
            survey: {
                type: Survey,
                required: true
            }
        },
        data() {
            return {
                possibleAnswers: [],
                answerCode: undefined,
                answerText: undefined
            }
        },
        watch: {
            survey: {
                immediate: true,
                deep: true,
                handler(newSurvey) {
                    watchSurvey(this, newSurvey);
                }
            }
        }
    }
    const Survey = require("../../model/Survey");

    function watchSurvey(component, newSurvey) {
        const possibleAnswers = newSurvey.questionDefinitions
            .filter(it => it.questionCode == component.$props.questionCode)[0]
            .possibleAnswers;
        component.possibleAnswers = possibleAnswers;

        const answer = newSurvey.answeredQuestions
            .filter(it => it.questionCode == component.$props.questionCode)[0]
        component.answerCode = answer ? answer.answerCode : undefined;
        component.answerText = answer ? answer.answerText : undefined;
    }
    watchSurvey.bind(this);
</script>

<style src="@/assets/survey.css">
</style>
