package pl.javorex.survey.application.response;

import java.util.Optional;

public final class AnsweredQuestionDto {
  public final String questionCode;
  public final String answerCode;
  public final Optional<String> answerText;

  public AnsweredQuestionDto(String questionCode, String answerCode, Optional<String> answerText) {
    this.questionCode = questionCode;
    this.answerCode = answerCode;
    this.answerText = answerText;
  }
}
