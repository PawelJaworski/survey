package pl.javorex.survey.application.validation;

import pl.javorex.survey.domain.AnsweredQuestion;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SurveyValidatorComposite implements SurveyValidator {
    private final List<ValidatorBuilder> validators;

    public static SurveyValidator compose(ValidatorBuilder... validators) {
        List<ValidatorBuilder> validatorList = Arrays.stream(validators)
                .collect(Collectors.toList());
        return new SurveyValidatorComposite(validatorList);
    }

    public SurveyValidatorComposite(List<ValidatorBuilder> validators) {
        this.validators = validators;
    }

    @Override
    public List<String> validate(SurveyDefinition surveyDefinition, List<AnsweredQuestion> answers) {
        return validators.stream()
                .map(ValidatorBuilder::build)
                .map(it -> it.validate(surveyDefinition, answers))
                .flatMap(it -> it.stream())
                .collect(Collectors.toList());
    }
}
