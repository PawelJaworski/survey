package pl.javorex.survey.domain.surveydefinition;

import java.util.Optional;

public final class QuestionGroup {
    private final Optional<String> code;

    public static QuestionGroup notAssigned() {
        return new QuestionGroup(Optional.empty());
    }
    public static QuestionGroup fromCode(String code) {
        return new QuestionGroup( Optional.of(code) );
    }

    public QuestionGroup(Optional<String> code) {
        this.code = code;
    }

    public boolean isAssigned() {
        return code.isPresent();
    }

    public String getCode() {
        return code
                .orElseThrow(() -> new IllegalStateException("Cannot get questionGroupCode. Question not assigned to any group."));
    }
}
