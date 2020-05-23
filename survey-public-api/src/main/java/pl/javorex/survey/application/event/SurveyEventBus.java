package pl.javorex.survey.application.event;

public interface SurveyEventBus {
    default void publish(SurveyAnsweredEvent event) {
        throw new IllegalStateException(event.getClass().getName() + " publishing not implemented");
    }
}
