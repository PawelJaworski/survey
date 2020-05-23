package pl.javorex.survey.application.response;

import java.util.Optional;

public final class AnsweredQuestionDto {
  private final String questionCode;
  private final String answerCode;
  private final String answerText;

  public AnsweredQuestionDto(String questionCode, String answerCode, Optional<String> answerText) {
    this.questionCode = questionCode;
    this.answerCode = answerCode;
    this.answerText = answerText.orElse(null);
  }

  public String getQuestionCode() {
    return questionCode;
  }

  public String getAnswerCode() {
    return answerCode;
  }

  public String getAnswerText() {
    return answerText;
  }
}
