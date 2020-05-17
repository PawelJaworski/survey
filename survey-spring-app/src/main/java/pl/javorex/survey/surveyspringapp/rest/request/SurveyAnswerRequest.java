package pl.javorex.survey.surveyspringapp.rest.request;

import java.util.Set;

public final class SurveyAnswerRequest {
    private Set<QuestionAnswerRequest> answers;

    private SurveyAnswerRequest() {}

    public SurveyAnswerRequest(Set<QuestionAnswerRequest> answers) {
        this.answers = answers;
    }

    public Set<QuestionAnswerRequest> getAnswers() {
        return answers;
    }
}
