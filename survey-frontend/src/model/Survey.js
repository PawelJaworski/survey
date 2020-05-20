export default {
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
