package pl.javorex.survey.surveyspringapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private final SurveyQueryFacade surveyQueryFacade;
    private final SurveyCommandFacade surveyCommandFacade;

    public SurveyController(SurveyQueryFacade surveyQueryFacade, SurveyCommandFacade surveyCommandFacade) {
        this.surveyQueryFacade = surveyQueryFacade;
        this.surveyCommandFacade = surveyCommandFacade;
    }

    @GetMapping("/surveys/{surveyType}/{version}/{respondentID}")
    public ResponseEntity<SurveyResponse> getSurvey(@PathVariable String surveyType, @PathVariable String version,
                                                    @PathVariable String respondentID) {
        SurveyByTypeAndVersionAndRespondentQuery<String> query = new SurveyByTypeAndVersionAndRespondentQuery<>(
                surveyType, version, respondentID );

        SurveyDto surveyDto = surveyQueryFacade.findSurveyByRespondentIDAndType( query );

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
