package pl.javorex.survey.application;

import pl.javorex.survey.application.adapter.SurveyDefinitionRepositoryInMemoryImpl;
import pl.javorex.survey.application.adapter.SurveyRepositoryInMemoryImpl;
import pl.javorex.survey.domain.AnsweredQuestion;
import pl.javorex.survey.domain.RespondentID;
import pl.javorex.survey.domain.Survey;
import pl.javorex.survey.domain.SurveyRepository;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinitionRepository;

import java.util.Optional;

public final class SurveyServiceConfig {
    private final SurveyDefinitionRepository surveyDefinitionRepository;
    private final SurveyRepository surveyRepository;

    public SurveyServiceConfig() {
        this(new SurveyDefinitionRepositoryInMemoryImpl(), new SurveyRepositoryInMemoryImpl());
        SurveyDefinition surveyDefinition = surveyDefinitionRepository
                .findByTypeAndVersion("FINANCIAL_NEEDS", "UNIT_LINKED.v1")
                .orElseThrow(() -> new IllegalStateException("Cannot find survey definition to init data."));
        Survey survey = new Survey(RespondentID.respondentIDOf("javorex"), surveyDefinition);
        survey.answer(new AnsweredQuestion("SOURCE_OF_INCOME", "GAMESTER", Optional.empty()));
        survey.answer(new AnsweredQuestion("PURPOSE_OF_SAVING", "OTHER", Optional.of("For cocaine.")));
        surveyRepository.save(survey);
    }

    public SurveyServiceConfig(SurveyDefinitionRepository surveyDefinitionRepository,
                               SurveyRepository surveyRepository) {
        this.surveyDefinitionRepository = surveyDefinitionRepository;
        this.surveyRepository = surveyRepository;
    }

    public SurveyQueryFacade surveyQueryFacade() {
        return new SurveyQueryFacadeImpl(surveyDefinitionRepository, surveyRepository);
    }

    public SurveyCommandFacade surveyCommandFacade() {
        return new SurveyCommandFacadeImpl(surveyDefinitionRepository, surveyRepository);
    }
}
