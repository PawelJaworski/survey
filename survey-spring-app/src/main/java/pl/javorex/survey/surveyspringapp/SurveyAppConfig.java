package pl.javorex.survey.surveyspringapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import pl.javorex.financialneeds.application.*;
import pl.javorex.survey.application.*;
import pl.javorex.survey.application.event.SurveyEventBus;

@Configuration
public class SurveyAppConfig {
    private final FinancialNeedsServiceConfig financialNeedsServiceConfig = new FinancialNeedsServiceConfig();
    private final FinancialNeedsCommandHandlers financialNeedsCommandHandlers = financialNeedsServiceConfig
            .financialNeedsCommandHandlers();
    private final SurveyEventBus<String> surveyEventBus = new SurveyEventBusSyncImpl(financialNeedsCommandHandlers);
    private final SurveyServiceConfig config = SurveyServiceConfig.builder()
            .withEventBus(surveyEventBus)
            .initTestData()
            .build();
    private final SurveyQueryHandlers surveyQueryHandlers = config.surveyQueryFacade();
    private final SurveyCommandFacadeImpl surveyCommandHandlers = config.surveyCommandFacade();
    private final FinancialNeedsSurveyConfig financialNeedsSurveyConfig = new FinancialNeedsSurveyConfig(
            surveyQueryHandlers, surveyCommandHandlers );

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }

    @Bean
    FinancialNeedsSurveyCommandHandlers financialNeedsSurveyCommandHandlers() {
        return financialNeedsSurveyConfig.getFinancialNeedsSurveyCommandHandlers();
    }
    @Bean
    FinancialNeedsSurveyQueryHandlers financialNeedsSurveyQueryHandlers() {
        return financialNeedsSurveyConfig.getFinancialNeedsSurveyQueryHandlers();
    }

    @Bean
    SurveyQueryHandlers surveyQueryFacade() {
        return surveyQueryHandlers;
    }

    @Bean
    SurveyCommandFacadeImpl surveyCommandHandlers() {
        return surveyCommandHandlers;
    }

    @Bean
    FinancialNeedsQueryFacadeImpl financialNeedsQueryFacade() {
        return financialNeedsServiceConfig.financialNeedsQueryFacade();
    }
}
