package pl.javorex.survey.surveyspringapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javorex.financialneeds.application.ActualFinancialNeedsSurveyQuery;
import pl.javorex.financialneeds.application.FinancialNeedsSurveyQueryHandlers;
import pl.javorex.survey.application.SurveyCommandFacade;
import pl.javorex.survey.application.SurveyQueryFacade;
import pl.javorex.survey.application.command.AnswerCmd;
import pl.javorex.survey.application.command.SurveyAnswerCmd;
import pl.javorex.survey.application.query.SurveyByTypeAndVersionAndRespondentQuery;
import pl.javorex.survey.application.response.ApiResult;
import pl.javorex.survey.application.response.SurveyDto;
import pl.javorex.survey.surveyspringapp.rest.request.SurveyAnswerRequest;
import pl.javorex.survey.surveyspringapp.rest.response.SurveyResponse;

import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SurveyController {
    private final SurveyCommandFacade surveyCommandFacade;
    private final FinancialNeedsSurveyQueryHandlers financialNeedsSurveyQueryHandlers;

    public SurveyController(SurveyCommandFacade surveyCommandFacade,
                            FinancialNeedsSurveyQueryHandlers financialNeedsSurveyQueryHandlers) {
        this.surveyCommandFacade = surveyCommandFacade;
        this.financialNeedsSurveyQueryHandlers = financialNeedsSurveyQueryHandlers;
    }

    @GetMapping("/surveys/actualFinancialNeeds/{respondentID}")
    public ResponseEntity<SurveyResponse> getSurvey(@PathVariable String respondentID) {
        ActualFinancialNeedsSurveyQuery<String> query = new ActualFinancialNeedsSurveyQuery<>(respondentID);

        SurveyDto surveyDto = financialNeedsSurveyQueryHandlers.findActualFinancialNeedsSurvey(query);

        return ResponseEntity.ok(
                new SurveyResponse( surveyDto.questionDefinitions, surveyDto.answeredQuestions )
        );
    }

    @PostMapping("/surveys/{surveyType}/{version}/{respondentID}/answer")
    public ResponseEntity<ApiResult> answerSurvey(@PathVariable String surveyType, @PathVariable String version,
                                                  @PathVariable String respondentID,
                                                  @RequestBody SurveyAnswerRequest answerRequest) {

        Set<AnswerCmd> answers = answerRequest.getAnswers()
                .stream()
                .map(it -> new AnswerCmd(it.getQuestionCode(), it.getAnswerCode(), it.getAnswerText()))
                .collect(Collectors.toSet());

        SurveyAnswerCmd cmd = new SurveyAnswerCmd( respondentID, surveyType, version,
                answers );
        ApiResult result = surveyCommandFacade.answerSurvey( cmd );

        return ResponseEntity.ok( result );
    }
}
