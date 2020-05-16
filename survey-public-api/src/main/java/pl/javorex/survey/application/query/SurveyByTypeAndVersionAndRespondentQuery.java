package pl.javorex.survey.application.query;

public final class SurveyByTypeAndVersionAndRespondentQuery<T> {
  public final String surveyType;
  public final String version;
  public final T respondentID;


  public SurveyByTypeAndVersionAndRespondentQuery(String surveyType, String version, T respondentID) {
    this.surveyType = surveyType;
    this.version = version;
    this.respondentID = respondentID;

  }
}
