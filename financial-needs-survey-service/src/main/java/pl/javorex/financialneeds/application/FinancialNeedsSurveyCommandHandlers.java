package pl.javorex.financialneeds.application;

import pl.javorex.financialneeds.application.command.FinancialNeedsAnswerCmd;
import pl.javorex.financialneeds.application.command.ScoringCalculationCmd;
import pl.javorex.survey.application.SurveyCommandHandlers;
import pl.javorex.survey.application.command.AnswerCmd;
import pl.javorex.survey.application.command.SurveyAnswerCmd;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.javorex.financialneeds.application.FinancialNeedsActualSurvey.*;

public final class FinancialNeedsSurveyCommandHandlers {
    private final SurveyCommandHandlers surveyCommandHandlers;
    private final FinancialNeedsCommandHandlers financialNeedsCommandHandlers;

    FinancialNeedsSurveyCommandHandlers(SurveyCommandHandlers surveyCommandHandlers,
                                        FinancialNeedsCommandHandlers financialNeedsCommandHandlers) {
        this.surveyCommandHandlers = surveyCommandHandlers;
        this.financialNeedsCommandHandlers = financialNeedsCommandHandlers;
    }

    public List<String> answerSurveyElseErrors(FinancialNeedsAnswerCmd cmd) {
        List<String> errors = surveyCommandHandlers.answerSurveyElseErrors(
                new SurveyAnswerCmd(cmd.getRespondentID(), SURVEY_TYPE, SURVEY_VERSION, cmd.getAnswers())
        );
        if (errors.size() > 0) {
            return errors;
        }

        Object customerID = cmd.getRespondentID();
        Map<String, String> answerByQuestion = cmd.getAnswers().stream()
                .collect(Collectors.toMap(AnswerCmd::getQuestionCode, AnswerCmd::getAnswerCode));
        ScoringCalculationCmd scoringCalculation = new ScoringCalculationCmd(customerID, answerByQuestion);
        financialNeedsCommandHandlers.calculateScoring(scoringCalculation);

        return Collections.emptyList();
    }
}
