package pl.javorex.survey.application.adapter;

import pl.javorex.survey.domain.surveydefinition.QuestionDefinition;
import pl.javorex.survey.domain.surveydefinition.QuestionGroup;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinitionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class SurveyDefinitionRepositoryInMemoryImpl implements SurveyDefinitionRepository {
    private final List<SurveyDefinition> surveyDefinitions = new ArrayList();

    public SurveyDefinitionRepositoryInMemoryImpl() {
        List< QuestionDefinition > questions = new ArrayList<>();
        questions.add( defineSourceOfIncome() );
        questions.add( defineAgeRange() );
        questions.add( definePurposeOfSaving() );

        surveyDefinitions.add(
                new SurveyDefinition( "FINANCIAL_NEEDS", "UNIT_LINKED.v1", questions )
        );
    }

    @Override
    public Optional<SurveyDefinition> findByTypeAndVersion(String surveyType, String surveyVersion) {
        return surveyDefinitions.stream()
                .filter(it -> it.isTypeOf(surveyType))
                .filter(it -> it.isVersionOf(surveyVersion))
                .findAny();
    }

    private QuestionDefinition defineSourceOfIncome() {
        String questionCode = "SOURCE_OF_INCOME";
        List<String> possibleAnswers = new ArrayList<>();
        possibleAnswers.add("GAMESTER");
        possibleAnswers.add("CONTRACT");
        possibleAnswers.add("UNEMPLOYED");

        return new QuestionDefinition(questionCode, possibleAnswers, QuestionGroup.notAssigned());
    }

    private QuestionDefinition defineAgeRange() {
        String questionCode = "AGE_RANGE";
        List<String> possibleAnswers = new ArrayList<>();
        possibleAnswers.add("FROM_18_TO_30");
        possibleAnswers.add("FROM_31_TO_60");
        possibleAnswers.add("OVER_60");

        return new QuestionDefinition(questionCode, possibleAnswers, QuestionGroup.notAssigned());
    }

    private QuestionDefinition definePurposeOfSaving() {
        String questionCode = "PURPOSE_OF_SAVING";
        List<String> possibleAnswers = new ArrayList<>();
        possibleAnswers.add("FOR_CHILDREN");
        possibleAnswers.add("FOR_HOLIDAY");
        possibleAnswers.add("OTHER");

        return new QuestionDefinition(questionCode, possibleAnswers, QuestionGroup.notAssigned());
    }
}
