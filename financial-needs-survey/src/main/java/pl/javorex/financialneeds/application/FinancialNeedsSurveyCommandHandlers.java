package pl.javorex.financialneeds.application;

import pl.javorex.financialneeds.application.command.FinancialNeedsAnswerCmd;
import pl.javorex.financialneeds.application.command.ScoringCalculationCmd;
import pl.javorex.survey.application.SurveyCommandHandlers;
import pl.javorex.survey.application.command.AnswerCmd;
import pl.javorex.survey.application.command.SurveyAnswerCmd;
import pl.javorex.survey.application.response.AnsweredQuestionDto;
import pl.javorex.survey.application.response.ApiResult;

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

    public ApiResult answerSurvey(FinancialNeedsAnswerCmd cmd) {
        ApiResult result = surveyCommandHandlers.answerSurvey(
                new SurveyAnswerCmd(cmd.getRespondentID(), SURVEY_TYPE, SURVEY_VERSION, cmd.getAnswers())
        );
        if (result.getErrors().size() > 0) {
            return result;
        }

        Object customerID = cmd.getRespondentID();
        Map<String, String> answerByQuestion = cmd.getAnswers().stream()
                .collect(Collectors.toMap(AnswerCmd::getQuestionCode, AnswerCmd::getAnswerCode));
        ScoringCalculationCmd scoringCalculation = new ScoringCalculationCmd(customerID, answerByQuestion);
        financialNeedsCommandHandlers.calculateScoring(scoringCalculation);

        return ApiResult.success();
    }
}
