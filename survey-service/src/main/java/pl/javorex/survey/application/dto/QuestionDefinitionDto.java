package pl.javorex.survey.application.dto;

import java.util.List;

public final class QuestionDefinitionDto {
  public final String questionCode;
  public final List<String> possibleAnswers;

  public QuestionDefinitionDto(String questionCode, List<String> possibleAnswers) {
    this.questionCode = questionCode;
    this.possibleAnswers = possibleAnswers;
  }
}
