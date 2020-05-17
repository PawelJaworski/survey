package pl.javorex.survey.domain;

import pl.javorex.survey.domain.surveydefinition.AnswerCode;
import pl.javorex.survey.domain.surveydefinition.QuestionCode;

import java.util.Optional;

public final class AnsweredQuestion {
  private final QuestionCode questionCode;
  private final AnswerCode answerCode;
  private final Optional<String> answerText;

  public AnsweredQuestion(String questionCode, String answerCode, Optional<String> answerText) {
    this.questionCode = new QuestionCode(questionCode);
    this.answerCode = new AnswerCode(answerCode);
    this.answerText = answerText;
  }

  public String getQuestionCode() {
    return questionCode.asString();
  }

  public String getAnswerCode() {
    return answerCode.asString();
  }

  public Optional<String> getAnswerText() {
    return answerText;
  }
}
