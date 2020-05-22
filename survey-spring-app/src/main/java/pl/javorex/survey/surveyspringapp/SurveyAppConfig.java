package pl.javorex.survey.surveyspringapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import pl.javorex.survey.application.SurveyCommandFacade;
import pl.javorex.survey.application.SurveyQueryFacade;
import pl.javorex.survey.application.SurveyServiceConfig;

@Configuration
public class SurveyAppConfig {
    private final SurveyServiceConfig config = new SurveyServiceConfig();

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
}
