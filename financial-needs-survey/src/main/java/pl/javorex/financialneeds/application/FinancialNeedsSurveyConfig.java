package pl.javorex.financialneeds.application;

import pl.javorex.survey.application.SurveyCommandHandlers;
import pl.javorex.survey.application.SurveyQueryHandlers;
import pl.javorex.survey.application.validation.AllQuestionsAnswered;
import pl.javorex.survey.application.validation.SurveyValidator;
import pl.javorex.survey.application.validation.SurveyValidatorComposite;

import static pl.javorex.financialneeds.application.FinancialNeedsActualSurvey.SURVEY_TYPE;
import static pl.javorex.financialneeds.application.FinancialNeedsActualSurvey.SURVEY_VERSION;

public final class FinancialNeedsSurveyConfig {
    private final FinancialNeedsSurveyQueryHandlers financialNeedsSurveyQueryHandlers;
    private final FinancialNeedsSurveyCommandHandlers financialNeedsSurveyCommandHandlers;

    public FinancialNeedsSurveyConfig(SurveyQueryHandlers surveyQueryHandlers,
                                      SurveyCommandHandlers surveyCommandHandlers,
                                      FinancialNeedsCommandHandlers financialNeedsCommandHandlers) {
        this.financialNeedsSurveyQueryHandlers = new FinancialNeedsSurveyQueryHandlers(surveyQueryHandlers);
        this.financialNeedsSurveyCommandHandlers = new FinancialNeedsSurveyCommandHandlers(surveyCommandHandlers, financialNeedsCommandHandlers);
        SurveyValidator validator = SurveyValidatorComposite.compose(new AllQuestionsAnswered("Answer obligatory"));

        surveyCommandHandlers.acceptValidatorForTypeAndVersion(validator, SURVEY_TYPE, SURVEY_VERSION);
    }

    public FinancialNeedsSurveyQueryHandlers getFinancialNeedsSurveyQueryHandlers() {
        return financialNeedsSurveyQueryHandlers;
    }

    public FinancialNeedsSurveyCommandHandlers getFinancialNeedsSurveyCommandHandlers() {
        return financialNeedsSurveyCommandHandlers;
    }
}
