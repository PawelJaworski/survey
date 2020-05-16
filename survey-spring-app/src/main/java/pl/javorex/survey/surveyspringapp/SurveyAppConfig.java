package pl.javorex.survey.surveyspringapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.javorex.survey.application.SurveyQueryFacade;
import pl.javorex.survey.application.SurveyServiceConfig;

@Configuration
public class SurveyAppConfig {
    private final SurveyServiceConfig config = new SurveyServiceConfig();

    @Bean
    SurveyQueryFacade surveyQueryFacade() {
        return config.surveyQueryFacade();
    }
}
