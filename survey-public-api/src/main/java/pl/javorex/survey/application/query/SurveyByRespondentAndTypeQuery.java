package pl.javorex.survey.application.query;

public final class SurveyByRespondentAndTypeQuery<R> {
  public final R respondentID;
  public final String surveyType;

  public SurveyByRespondentAndTypeQuery(R respondentID, String surveyType) {
    this.respondentID = respondentID;
    this.surveyType = surveyType;
  }
}
