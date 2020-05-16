package pl.javorex.survey.application;

import pl.javorex.survey.application.adapter.SurveyDefinitionRepositoryInMemoryImpl;
import pl.javorex.survey.application.adapter.SurveyRepositoryInMemoryImpl;
import pl.javorex.survey.domain.SurveyRepository;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinitionRepository;

public final class SurveyServiceConfig {
    private final SurveyDefinitionRepository surveyDefinitionRepository;
    private final SurveyRepository surveyRepository;

    public SurveyServiceConfig() {
        this(new SurveyDefinitionRepositoryInMemoryImpl(), new SurveyRepositoryInMemoryImpl());
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
