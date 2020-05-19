<template>
    <div v-if="survey" class="survey">
        <div>{{questionCode}} - {{possibleAnswers}} - {{answerCode}}</div>
        <div v-for="possibleAnswer in possibleAnswers"
             v-bind:key="possibleAnswer.answerCode">
            {{possibleAnswer.answerCode}}
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
                answerCode: undefined
            }
        },
        watch: {
            survey: {
                immediate: true,
                deep: true,
                handler(newSurvey) {
                    const possibleAnswers = newSurvey.questionDefinitions
                        .filter(it => it.questionCode == this.$props.questionCode)[0]
                        .possibleAnswers;
                    this.possibleAnswers = possibleAnswers;

                    const answerCode = newSurvey.answeredQuestions
                        .filter(it => it.questionCode == this.$props.questionCode)[0]
                    this.answerCode = answerCode ? answerCode.answerCode : undefined;
                }
            }
        }
    }
    const Survey = {
        questionDefinitions: {
            type: Array,
            default: () => [{
                questionCode: String,
                possibleAnswers: Array
            }]
        },
        answeredQuestions: {
            type: Array,
            default: () => [{
                questionCode: "",
                answerCode: "",
                answerText: ""
            }]
        }
    }
</script>

<style scoped>

</style>
