package pl.javorex.survey.surveyspringapp;

import pl.javorex.financialneeds.application.FinancialNeedsCommandHandlers;
import pl.javorex.financialneeds.application.command.ScoringCalculationCmd;
import pl.javorex.survey.application.event.SurveyAnsweredEvent;
import pl.javorex.survey.application.event.SurveyEventBus;
import pl.javorex.survey.application.response.AnsweredQuestionDto;
import pl.javorex.survey.domain.AnsweredQuestion;

import java.util.Map;
import java.util.stream.Collectors;

import static pl.javorex.survey.surveyspringapp.SurveyVersionsProps.FINANCIAL_NEEDS_TYPE;
import static pl.javorex.survey.surveyspringapp.SurveyVersionsProps.FINANCIAL_NEEDS_VERSION;

public final class SurveyEventBusSyncImpl implements SurveyEventBus<String> {
    private final FinancialNeedsCommandHandlers financialNeedsCommandHandlers;

    public SurveyEventBusSyncImpl(FinancialNeedsCommandHandlers financialNeedsCommandHandlers) {
        this.financialNeedsCommandHandlers = financialNeedsCommandHandlers;
    }

    @Override
    public void publish(SurveyAnsweredEvent<String> event) {
        if (isEventForActualFinancialNeedsSurvey(event)) {
            Map<String, String> answerByQuestion = event.getAnsweredQuestions().stream()
                    .collect(Collectors.toMap(AnsweredQuestionDto::getQuestionCode, AnsweredQuestionDto::getAnswerCode));
            ScoringCalculationCmd<String> cmd = new ScoringCalculationCmd<>(event.getRespondentID(), answerByQuestion);
            financialNeedsCommandHandlers.calculateScoring(cmd);
        }
    }

    private boolean isEventForActualFinancialNeedsSurvey(SurveyAnsweredEvent<String> event) {
        return event.getSurveyType().equals(FINANCIAL_NEEDS_TYPE)
                && event.getSurveyVersion().equals(FINANCIAL_NEEDS_VERSION);
    }
}
