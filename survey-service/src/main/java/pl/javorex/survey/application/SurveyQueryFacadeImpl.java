package pl.javorex.survey.application;

import pl.javorex.survey.application.query.SurveyByTypeAndVersionAndRespondentQuery;
import pl.javorex.survey.application.response.QuestionDefinitionDto;
import pl.javorex.survey.application.response.SurveyDto;
import pl.javorex.survey.domain.RespondentID;
import pl.javorex.survey.domain.Survey;
import pl.javorex.survey.domain.SurveyRepository;
import pl.javorex.survey.domain.surveydefinition.QuestionDefinition;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinitionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static pl.javorex.survey.domain.RespondentID.respondentIDOf;

final class SurveyQueryFacadeImpl implements SurveyQueryFacade {
  private final SurveyDefinitionRepository surveyDefinitionRepository;
  private final SurveyRepository surveyRepository;

  private final SurveyDtoFactory surveyDtoFactory = new SurveyDtoFactory();

  SurveyQueryFacadeImpl(SurveyDefinitionRepository surveyDefinitionRepository,
                               SurveyRepository surveyRepository) {
    this.surveyDefinitionRepository = surveyDefinitionRepository;
    this.surveyRepository = surveyRepository;
  }

  @Override
  public SurveyDto findSurveyByRespondentIDAndType(SurveyByTypeAndVersionAndRespondentQuery query) {
    RespondentID respondentID =  respondentIDOf( query.respondentID );
    String surveyType = query.surveyType;
    String surveyVersion = query.version;

    SurveyDefinition surveyDefinition =  surveyDefinitionRepository
            .findByTypeAndVersion(surveyType, surveyVersion)
            .orElseThrow(() -> new NoSuchElementException("SurveyDefinition not found for typeCode: " + surveyType
                    + " and versionCode" + surveyVersion));

    Optional<Survey> foundSurvey = surveyRepository
            .findByRespondentIDAndType(respondentID, surveyType);

    return foundSurvey
            .map(surveyDtoFactory::fromSurvey)
            .orElseGet(() -> surveyDtoFactory.empty(surveyDefinition));
  }

}
