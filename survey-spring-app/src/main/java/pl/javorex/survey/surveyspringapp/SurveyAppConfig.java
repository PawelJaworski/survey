package pl.javorex.survey.surveyspringapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import pl.javorex.financialneeds.application.FinancialNeedsCommandHandlers;
import pl.javorex.financialneeds.application.FinancialNeedsQueryFacadeImpl;
import pl.javorex.financialneeds.application.FinancialNeedsServiceConfig;
import pl.javorex.financialneeds.application.query.FinancialNeedsQueryFacade;
import pl.javorex.survey.application.SurveyCommandFacade;
import pl.javorex.survey.application.SurveyQueryFacade;
import pl.javorex.survey.application.SurveyServiceConfig;
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


    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }

    @Bean
    SurveyQueryFacade surveyQueryFacade() {
        return config.surveyQueryFacade();
    }

    @Bean
    SurveyCommandFacade surveyCommandFacade() {
        return config.surveyCommandFacade();
    }

    @Bean
    FinancialNeedsQueryFacadeImpl financialNeedsQueryFacade() {
        return financialNeedsServiceConfig.financialNeedsQueryFacade();
    }
}
