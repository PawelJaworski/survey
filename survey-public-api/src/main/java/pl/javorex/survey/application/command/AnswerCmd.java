package pl.javorex.survey.application.command;

import java.util.Optional;

public final class AnswerCmd {
  public final String questionCode;
  public final String answerCode;
  public final Optional<String> answerText;

  public AnswerCmd(String questionCode, String answerCode, String answerText) {
    this.questionCode = questionCode;
    this.answerCode = answerCode;
    this.answerText = Optional.ofNullable(answerText);
  }
}
