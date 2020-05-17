package pl.javorex.survey.application;

import pl.javorex.survey.application.command.SurveyAnswerCmd;
import pl.javorex.survey.application.response.ApiResult;

public interface SurveyCommandFacade{
  ApiResult answerSurvey(SurveyAnswerCmd cmd);
}
