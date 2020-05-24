package pl.javorex.survey.surveyspringapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javorex.financialneeds.application.ActualFinancialNeedsSurveyQuery;
import pl.javorex.financialneeds.application.FinancialNeedsSurveyCommandHandlers;
import pl.javorex.financialneeds.application.FinancialNeedsSurveyQueryHandlers;
import pl.javorex.financialneeds.application.command.FinancialNeedsAnswerCmd;
import pl.javorex.survey.application.command.AnswerCmd;
import pl.javorex.survey.application.dto.SurveyDto;
import pl.javorex.survey.surveyspringapp.rest.request.SurveyAnswerRequest;
import pl.javorex.survey.surveyspringapp.rest.response.SurveyResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SurveyController {
    private final FinancialNeedsSurveyCommandHandlers financialNeedsSurveyCommandHandlers;
    private final FinancialNeedsSurveyQueryHandlers financialNeedsSurveyQueryHandlers;

    public SurveyController(FinancialNeedsSurveyCommandHandlers financialNeedsSurveyCommandHandlers,
                            FinancialNeedsSurveyQueryHandlers financialNeedsSurveyQueryHandlers) {
        this.financialNeedsSurveyCommandHandlers = financialNeedsSurveyCommandHandlers;
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

    @PostMapping("/surveys/financialNeeds/{respondentID}/answer")
    public ResponseEntity answerSurvey(@PathVariable String respondentID,
                                                  @RequestBody SurveyAnswerRequest answerRequest) {

        Set<AnswerCmd> answers = answerRequest.getAnswers()
                .stream()
                .map(it -> new AnswerCmd(it.getQuestionCode(), it.getAnswerCode(), it.getAnswerText()))
                .collect(Collectors.toSet());

        FinancialNeedsAnswerCmd cmd = new FinancialNeedsAnswerCmd(respondentID, answers);
        List<String> errors = financialNeedsSurveyCommandHandlers.answerSurveyElseErrors(cmd);

        if (errors.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(errors);
        }

        return ResponseEntity.ok()
                .build();
    }
}
