package pl.javorex.survey.application.command;

import java.util.Optional;

public final class AnswerCmd {
  private final String questionCode;
  private final String answerCode;
  private final Optional<String> answerText;

  public AnswerCmd(String questionCode, String answerCode, String answerText) {
    this.questionCode = questionCode;
    this.answerCode = answerCode;
    this.answerText = Optional.ofNullable(answerText);
  }

  public String getQuestionCode() {
    return questionCode;
  }

  public String getAnswerCode() {
    return answerCode;
  }

  public Optional<String> getAnswerText() {
    return answerText;
  }
}
