package pl.javorex.survey.domain.surveydefinition;

import java.util.List;

public final class SurveyDefinition {
  private final SurveyType type;
  private final SurveyVersion version;
  private final List<QuestionDefinition> questions;

  public SurveyDefinition(String type, String version,
                          List<QuestionDefinition> questions) {
    this.type = new SurveyType(type);
    this.version = new SurveyVersion(version);
    this.questions = questions;
  }

  public boolean isTypeOf(String type) {
    return this.type
            .asString()
            .equals(type);
  }
  public String getType() {
    return type.asString();
  }

  public boolean isVersionOf(String version) {
    return this.version
            .asString()
            .equals(version);
  }

  public String getVersion() {
    return version.asString();
  }

  public List<QuestionDefinition> getQuestions() {
    return questions;
  }

  public boolean hasSameIdentityAs(SurveyDefinition other) {
    return other.type.isSameAs(type)
            && other.version.isSameAs(version);
  }
}
