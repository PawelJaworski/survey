package pl.javorex.survey.application.adapter;

import pl.javorex.survey.application.event.SurveyAnsweredEvent;
import pl.javorex.survey.application.event.SurveyEventBus;

public class SurveyEventBusSystemOutImpl implements SurveyEventBus {
    @Override
    public void publish(SurveyAnsweredEvent event) {
        System.out.println(event + " occurred.");
    }
}
