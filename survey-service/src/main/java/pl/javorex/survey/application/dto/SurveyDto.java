package pl.javorex.survey.application.dto;

import java.util.List;

public final class SurveyDto {
  public final List<QuestionDefinitionDto> questionDefinitions;
  public final List<AnsweredQuestionDto> answeredQuestions;

  public SurveyDto(List<QuestionDefinitionDto> questionDefinitions, List<AnsweredQuestionDto> answeredQuestions) {
    this.questionDefinitions = questionDefinitions;
    this.answeredQuestions = answeredQuestions;
  }
}
