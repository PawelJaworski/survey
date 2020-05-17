package pl.javorex.survey.application;

import pl.javorex.survey.application.command.SurveyAnswerCmd;
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

  public SurveyCommandFacadeImpl(SurveyDefinitionRepository surveyDefinitionRepository,
                                 SurveyRepository<?> surveyRepository) {
    this.surveyDefinitionRepository = surveyDefinitionRepository;
    this.surveyRepository = surveyRepository;
  }

  @Override
  public ApiResult answerSurvey(SurveyAnswerCmd cmd) {
    RespondentID<?> respondentID = RespondentID.respondentIDOf(cmd.respondentID);
    String surveyType = cmd.surveyType;
    String surveyVersion = cmd.surveyVersion;

    SurveyDefinition surveyDefinition = surveyDefinitionRepository.findByTypeAndVersion(surveyType, surveyVersion)
            .orElseThrow(() -> new SurveyNotFoundException(surveyType, surveyVersion));

    Optional<Survey> foundSurvey = surveyRepository.findByRespondentIDAndType(respondentID, surveyType);
    Survey survey = foundSurvey
            .orElseGet(() -> new Survey(respondentID, surveyDefinition));

    List<AnsweredQuestion> answeredQuestions = cmd.answers.stream()
            .map(it -> new AnsweredQuestion( it.questionCode, it.answerCode, it.answerText ))
            .collect(Collectors.toList());

    survey.answerAll(answeredQuestions);

    surveyRepository.save(survey);

    return ApiResult.success();
  }
}
