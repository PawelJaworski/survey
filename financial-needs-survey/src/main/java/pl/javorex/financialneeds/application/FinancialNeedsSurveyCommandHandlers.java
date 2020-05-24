package pl.javorex.financialneeds.application;

import pl.javorex.financialneeds.application.command.FinancialNeedsAnswerCmd;
import pl.javorex.survey.application.SurveyCommandFacadeImpl;
import pl.javorex.survey.application.command.SurveyAnswerCmd;
import pl.javorex.survey.application.response.ApiResult;

import static pl.javorex.financialneeds.application.FinancialNeedsActualSurvey.*;

public final class FinancialNeedsSurveyCommandHandlers {
    private final SurveyCommandFacadeImpl surveyCommandHandlers;

    FinancialNeedsSurveyCommandHandlers(SurveyCommandFacadeImpl surveyCommandHandlers) {
        this.surveyCommandHandlers = surveyCommandHandlers;
    }

    public ApiResult answerSurvey(FinancialNeedsAnswerCmd cmd) {
        return surveyCommandHandlers.answerSurvey(
                new SurveyAnswerCmd(cmd.getRespondentID(), SURVEY_TYPE, SURVEY_VERSION, cmd.getAnswers())
        );
    }
}
