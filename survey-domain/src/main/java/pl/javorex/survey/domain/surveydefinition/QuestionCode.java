package pl.javorex.survey.domain.surveydefinition;

public final class QuestionCode {
  private final String code;

  public QuestionCode(String code) {
    this.code = code;
  }

  public String asString() {
    return code;
  }
}
