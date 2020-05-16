package pl.javorex.survey.surveyspringapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.javorex.survey.application.SurveyQueryFacade;
import pl.javorex.survey.application.query.SurveyByTypeAndVersionAndRespondentQuery;
import pl.javorex.survey.application.response.SurveyDto;
import pl.javorex.survey.surveyspringapp.rest.response.SurveyResponse;

@RestController
public class SurveyController {
    private final SurveyQueryFacade surveyQueryFacade;

    public SurveyController(SurveyQueryFacade surveyQueryFacade) {
        this.surveyQueryFacade = surveyQueryFacade;
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
}
