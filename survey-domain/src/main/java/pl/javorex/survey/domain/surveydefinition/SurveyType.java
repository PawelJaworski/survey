package pl.javorex.survey.domain.surveydefinition;

public final class SurveyType {
  private final String raw;

  public SurveyType(String raw) {
    this.raw = raw;
  }

  public String asString() {
    return raw;
  }

  public boolean isSameAs(SurveyType other) {
    return this.raw.equals(other.raw);
  }

  @Override
  public String toString() {
    return raw;
  }
}
