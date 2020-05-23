package pl.javorex.survey.application.event;

public interface SurveyEventBus<R> {
    void publish(SurveyAnsweredEvent<R> event);
}
