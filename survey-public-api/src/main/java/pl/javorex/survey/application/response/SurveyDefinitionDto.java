package pl.javorex.survey.application.response;

import java.util.List;

public final class SurveyDefinitionDto {
  public final List<QuestionDefinitionDto> questions;

  public SurveyDefinitionDto(String type, String version, List<QuestionDefinitionDto> questions) {
    this.questions = questions;
  }
}
