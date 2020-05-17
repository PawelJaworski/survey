package pl.javorex.survey.surveyspringapp.rest.request;

public class QuestionAnswerRequest {
    private String questionCode;
    private String answerCode;
    private String answerText;

    private QuestionAnswerRequest() {}

    public QuestionAnswerRequest(String questionCode, String answerCode, String answerText) {
        this.questionCode = questionCode;
        this.answerCode = answerCode;
        this.answerText = answerText;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getQuestionCode() {
        return questionCode;
    }
}
