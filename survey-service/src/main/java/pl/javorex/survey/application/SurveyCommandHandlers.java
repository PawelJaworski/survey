package pl.javorex.survey.application;

import pl.javorex.survey.application.command.SurveyAnswerCmd;
import pl.javorex.survey.application.exception.SurveyNotFoundException;
import pl.javorex.survey.application.response.ApiResult;
import pl.javorex.survey.application.validation.SurveyValidator;
import pl.javorex.survey.domain.AnsweredQuestion;
import pl.javorex.survey.domain.RespondentID;
import pl.javorex.survey.domain.Survey;
import pl.javorex.survey.domain.SurveyRepository;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinitionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SurveyCommandHandlers {
  private final SurveyDefinitionRepository surveyDefinitionRepository;
  private final SurveyRepository surveyRepository;
  private final SurveyValidatorsStore surveyValidatorsStore = new SurveyValidatorsStore();

  public SurveyCommandHandlers(SurveyDefinitionRepository surveyDefinitionRepository,
                               SurveyRepository<?> surveyRepository) {
    this.surveyDefinitionRepository = surveyDefinitionRepository;
    this.surveyRepository = surveyRepository;
  }

  public void acceptValidatorForTypeAndVersion(SurveyValidator validator, String type, String version) {
    surveyValidatorsStore.setValidatorForTypeAndVersion(validator, type, version);
  }

  public List<String> answerSurveyElseErrors(SurveyAnswerCmd cmd) {
    try {
      return tryAnswerSurvey(cmd);
    } catch(Exception e) {
      return Collections.singletonList(e.getMessage());
    }
  }

  private List<String> tryAnswerSurvey(SurveyAnswerCmd cmd){
    RespondentID<?> respondentID = RespondentID.respondentIDOf(cmd.respondentID);
    String surveyType = cmd.surveyType;
    String surveyVersion = cmd.surveyVersion;

    SurveyDefinition surveyDefinition = surveyDefinitionRepository.findByTypeAndVersion(surveyType, surveyVersion)
            .orElseThrow(() -> new SurveyNotFoundException(surveyType, surveyVersion));

    List<AnsweredQuestion> answeredQuestions = cmd.answers.stream()
            .map(it -> new AnsweredQuestion( it.getQuestionCode(), it.getAnswerCode(), it.getAnswerText() ))
            .collect(Collectors.toList());

    List<String> validationErrors = validate(surveyDefinition, answeredQuestions);
    if (validationErrors.size() > 0){
      return validationErrors;
    }

    Optional<Survey> foundSurvey = surveyRepository.findByRespondentIDAndType(respondentID, surveyType);
    Survey survey = foundSurvey
            .orElseGet(() -> new Survey(respondentID, surveyDefinition));
    survey.answerAll(answeredQuestions);

    surveyRepository.save(survey);

    return Collections.emptyList();
  }

  private List<String> validate(SurveyDefinition surveyDefinition, List<AnsweredQuestion> answeredQuestions) {
    Optional<SurveyValidator> validator = surveyValidatorsStore
            .getForSurveyTypeAndVersion( surveyDefinition.getType(), surveyDefinition.getVersion() );

    if (!validator.isPresent()) {
      return Collections.emptyList();
    }

    return validator.get().validate(surveyDefinition, answeredQuestions);
  }
}
