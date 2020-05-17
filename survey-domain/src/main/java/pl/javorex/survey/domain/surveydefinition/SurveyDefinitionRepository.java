package pl.javorex.survey.domain.surveydefinition;

import java.util.Optional;

public interface SurveyDefinitionRepository {
  Optional<SurveyDefinition> findByTypeAndVersion(String surveyType, String surveyVersion);
}
