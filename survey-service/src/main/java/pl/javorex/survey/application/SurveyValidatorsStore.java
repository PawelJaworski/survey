package pl.javorex.survey.application;

import pl.javorex.survey.application.validation.SurveyValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SurveyValidatorsStore {
    private Map<String, Map<String, SurveyValidator>> validatorByTypeAndVersion = new HashMap<>();

    void setValidatorForTypeAndVersion(SurveyValidator validator, String type, String version) {
        if (!validatorByTypeAndVersion.containsKey(type)) {
            validatorByTypeAndVersion.put(type, new HashMap<>());
        }

        validatorByTypeAndVersion.get(type)
                .put(version, validator);
    }

    public Optional<SurveyValidator> getForSurveyTypeAndVersion(String surveyType, String surveyVersion) {
        if (validatorByTypeAndVersion.containsKey(surveyType)) {
            if (validatorByTypeAndVersion.get(surveyType).containsKey(surveyVersion)) {
                return Optional.of( validatorByTypeAndVersion.get(surveyType)
                        .get(surveyVersion) );
            }
        }

        return Optional.empty();
    }
}
