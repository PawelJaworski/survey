package pl.javorex.survey.application;

import pl.javorex.survey.application.command.SurveyAnswerCmd;
import pl.javorex.survey.application.event.SurveyAnsweredEvent;
import pl.javorex.survey.application.event.SurveyEventBus;
import pl.javorex.survey.application.exception.SurveyNotFoundException;
import pl.javorex.survey.application.response.ApiResult;
import pl.javorex.survey.domain.AnsweredQuestion;
import pl.javorex.survey.domain.RespondentID;
import pl.javorex.survey.domain.Survey;
import pl.javorex.survey.domain.SurveyRepository;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinitionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SurveyCommandFacadeImpl implements SurveyCommandFacade {
  private final SurveyDefinitionRepository surveyDefinitionRepository;
  private final SurveyRepository surveyRepository;
  private final SurveyEventBus eventBus;

  public SurveyCommandFacadeImpl(SurveyDefinitionRepository surveyDefinitionRepository,
                                 SurveyRepository<?> surveyRepository, SurveyEventBus eventBus) {
    this.surveyDefinitionRepository = surveyDefinitionRepository;
    this.surveyRepository = surveyRepository;
    this.eventBus = eventBus;
  }

  @Override
  public ApiResult answerSurvey(SurveyAnswerCmd cmd) {
    try {
      return tryAnswerSurvey(cmd);
    } catch(Exception e) {
      return ApiResult.failureOf(e.getMessage());
    }
  }

  private ApiResult tryAnswerSurvey(SurveyAnswerCmd cmd){
    RespondentID<?> respondentID = RespondentID.respondentIDOf(cmd.respondentID);
    String surveyType = cmd.surveyType;
    String surveyVersion = cmd.surveyVersion;

    SurveyDefinition surveyDefinition = surveyDefinitionRepository.findByTypeAndVersion(surveyType, surveyVersion)
            .orElseThrow(() -> new SurveyNotFoundException(surveyType, surveyVersion));

    List<AnsweredQuestion> answeredQuestions = cmd.answers.stream()
            .map(it -> new AnsweredQuestion( it.questionCode, it.answerCode, it.answerText ))
            .collect(Collectors.toList());

    Optional<Survey> foundSurvey = surveyRepository.findByRespondentIDAndType(respondentID, surveyType);
    Survey survey = foundSurvey
            .orElseGet(() -> new Survey(respondentID, surveyDefinition));
    survey.answerAll(answeredQuestions);

    surveyRepository.save(survey);

    eventBus.publish(
            new SurveyAnsweredEvent(respondentID.getRaw(), surveyType, surveyVersion, answeredQuestions)
    );

    return ApiResult.success();
  }
}
