package pl.javorex.survey.application.validation;

import pl.javorex.survey.domain.AnsweredQuestion;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;

import java.util.List;

public interface SurveyValidator {
    List<String> validate(SurveyDefinition surveyDefinition, List<AnsweredQuestion> answers);
}
