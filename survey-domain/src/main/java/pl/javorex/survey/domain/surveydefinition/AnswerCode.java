package pl.javorex.survey.domain.surveydefinition;

public final class AnswerCode {
    private final String code;

    public AnswerCode(String code) {
        this.code = code;
    }

    public String asString() {
        return code;
    }
}
