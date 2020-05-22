package pl.javorex.survey.domain;

import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;
import pl.javorex.survey.domain.surveydefinition.SurveyType;

import java.util.ArrayList;
import java.util.List;

public final class Survey {
  private final RespondentID<?> respondentID;
  private final SurveyDefinition definition;
  private List<AnsweredQuestion> answeredQuestions;

  public Survey(RespondentID<?> respondentID, SurveyDefinition definition) {
    this.respondentID = respondentID;
    this.definition = definition;
    this.answeredQuestions = new ArrayList<>();
  }

  public RespondentID<?> getRespondentID() {
    return respondentID;
  }

  public List<AnsweredQuestion> getAnsweredQuestions() {
    return answeredQuestions;
  }

  public void answerAll(List<AnsweredQuestion> answeredQuestions) {
    this.answeredQuestions = answeredQuestions;
  }

  public void answer(AnsweredQuestion answeredQuestion) {
    this.answeredQuestions.add(answeredQuestion);
  }

  public SurveyDefinition getDefinition() {
    return definition;
  }

  public boolean hasSameIdentityAs(Survey other) {
    return other.respondentID.isSameAs(respondentID)
            && other.definition.hasSameIdentityAs(definition);
  }

  public boolean isAnsweredBy(RespondentID<?> respondentID) {
    return this.respondentID.isSameAs(respondentID);
  }

  public boolean isTypeOf(String surveyType) {
    return definition.getType()
            .equals(surveyType);
  }
}
