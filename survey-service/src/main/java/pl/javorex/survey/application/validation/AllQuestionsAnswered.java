package pl.javorex.survey.application.validation;

import pl.javorex.survey.domain.AnsweredQuestion;
import java.util.Set;
import java.util.stream.Collectors;

public class AllQuestionsAnswered implements ValidatorBuilder {
    private final String message;
    public AllQuestionsAnswered(String message) {
        this.message = message;
    }

    @Override
    public SurveyValidator build() {
        return (surveyDefinition, answers) -> {
             Set<String> questionCodes = answers.stream()
                     .map(AnsweredQuestion::getQuestionCode)
                     .collect(Collectors.toSet());

             return surveyDefinition.getQuestions().stream()
                     .map(it -> it.getQuestionCode())
                     .filter(it -> !questionCodes.contains(it))
                     .map(it -> "["+ it +"] " + message)
                     .collect(Collectors.toList());
        };
    }
}
