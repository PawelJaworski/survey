package pl.javorex.survey.surveyspringapp.rest.response;

import pl.javorex.survey.application.dto.AnsweredQuestionDto;
import pl.javorex.survey.application.dto.QuestionDefinitionDto;

import java.util.List;

public class SurveyResponse {
    private final List<QuestionDefinitionDto> questionDefinitions;
    private final List<AnsweredQuestionDto> answeredQuestions;

    public SurveyResponse(List<QuestionDefinitionDto> questionDefinitions,
                          List<AnsweredQuestionDto> answeredQuestions) {
        this.questionDefinitions = questionDefinitions;
        this.answeredQuestions = answeredQuestions;
    }

    public List<QuestionDefinitionDto> getQuestionDefinitions() {
        return questionDefinitions;
    }

    public List<AnsweredQuestionDto> getAnsweredQuestions() {
        return answeredQuestions;
    }
}
