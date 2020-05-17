package pl.javorex.survey.domain;

import pl.javorex.survey.domain.surveydefinition.SurveyType;

import java.util.Optional;

public interface SurveyRepository<R> {
  Survey save(Survey survey);
  Optional<Survey> findByRespondentIDAndType(RespondentID<R> id, String type);
}
