package pl.javorex.survey.application.exception;

public class SurveyNotFoundException extends RuntimeException {
    public SurveyNotFoundException(String type, String version) {
        super("Survey not found for type: " + type + " and version: " + version);
    }
}
