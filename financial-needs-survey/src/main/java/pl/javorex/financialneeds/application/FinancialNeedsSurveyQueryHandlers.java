package pl.javorex.financialneeds.application;

import pl.javorex.survey.application.SurveyQueryFacade;
import pl.javorex.survey.application.SurveyQueryHandlers;
import pl.javorex.survey.application.query.SurveyByTypeAndVersionAndRespondentQuery;
import pl.javorex.survey.application.response.SurveyDto;

import static pl.javorex.financialneeds.application.FinancialNeedsActualSurvey.SURVEY_TYPE;
import static pl.javorex.financialneeds.application.FinancialNeedsActualSurvey.SURVEY_VERSION;

public final class FinancialNeedsSurveyQueryHandlers<R> {
    private final SurveyQueryHandlers surveyQueryHandlers;

    public FinancialNeedsSurveyQueryHandlers(SurveyQueryHandlers surveyQueryHandlers) {
        this.surveyQueryHandlers = surveyQueryHandlers;
    }

    public SurveyDto findActualFinancialNeedsSurvey(ActualFinancialNeedsSurveyQuery<R> query) {
        R respondentID = query.getRespondentID();

        return surveyQueryHandlers.findSurveyByRespondentIDAndType(
                new SurveyByTypeAndVersionAndRespondentQuery(SURVEY_TYPE, SURVEY_VERSION, respondentID)
        );
    }
}
