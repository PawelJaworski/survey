package pl.javorex.financialneeds.application;

import pl.javorex.survey.application.SurveyCommandFacadeImpl;
import pl.javorex.survey.application.SurveyQueryHandlers;

public final class FinancialNeedsSurveyConfig {
    private final FinancialNeedsSurveyQueryHandlers financialNeedsSurveyQueryHandlers;
    private final FinancialNeedsSurveyCommandHandlers financialNeedsSurveyCommandHandlers;

    public FinancialNeedsSurveyConfig(SurveyQueryHandlers surveyQueryHandlers,
                                      SurveyCommandFacadeImpl surveyCommandHandlers) {
        this.financialNeedsSurveyQueryHandlers = new FinancialNeedsSurveyQueryHandlers(surveyQueryHandlers);
        this.financialNeedsSurveyCommandHandlers = new FinancialNeedsSurveyCommandHandlers(surveyCommandHandlers);
    }

    public FinancialNeedsSurveyQueryHandlers getFinancialNeedsSurveyQueryHandlers() {
        return financialNeedsSurveyQueryHandlers;
    }

    public FinancialNeedsSurveyCommandHandlers getFinancialNeedsSurveyCommandHandlers() {
        return financialNeedsSurveyCommandHandlers;
    }
}
