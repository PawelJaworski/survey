export const SurveyStore = {
    answers: {},

    setAnswer(questionCode, answerCode, answerText) {
        this.answers[questionCode] = {};
        this.addAnswer(questionCode, answerCode, answerText);
    },
    addAnswer(questionCode, answerCode, answerText) {
        const questionAnswers = this.answers[questionCode] || {};
        questionAnswers[answerCode] = answerText || '';
        this.answers[questionCode] = questionAnswers;

        //console.log('question ' + questionCode + ' answered: ' + JSON.stringify(this.answers[questionCode]))
    },

    getAnswers() {
        return this.answers;
    }
}
