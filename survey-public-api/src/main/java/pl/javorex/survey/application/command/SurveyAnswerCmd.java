package pl.javorex.survey.application.command;

import java.util.Collections;
import java.util.Set;

public final class SurveyAnswerCmd {
  public final Object respondentID;
  public final String surveyType;
  public final String surveyVersion;
  public final Set<AnswerCmd> answers;

  public SurveyAnswerCmd(Object respondentID, String surveyType, String surveyVersion,
                         Set<AnswerCmd> answers) {
    this.respondentID = respondentID;
    this.surveyType = surveyType;
    this.surveyVersion = surveyVersion;
    this.answers = Collections.unmodifiableSet(answers);
  }
}
