package pl.javorex.survey.domain.surveydefinition;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class QuestionDefinition {
    private final QuestionCode questionCode;
    private final List<AnswerCode> possibleAnswers;
    private final QuestionGroup questionGroup;

    public QuestionDefinition(String questionCode, List<String> possibleAnswers, QuestionGroup questionGroup) {
        this.questionCode = new QuestionCode(questionCode);
        this.possibleAnswers = Objects.requireNonNull(possibleAnswers).stream()
            .map(AnswerCode::new)
            .collect(Collectors.toList());
        this.questionGroup = questionGroup;
    }

    public String getQuestionCode() {
        return questionCode.asString();
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers.stream()
                .map(AnswerCode::asString)
                .collect(Collectors.toList());
    }

    public QuestionGroup getQuestionGroup() {
        return questionGroup;
    }
}
