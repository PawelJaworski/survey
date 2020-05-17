package pl.javorex.survey.domain.surveydefinition;

final class SurveyVersion {
    private final String raw;

    SurveyVersion(String raw) {
        this.raw = raw;
    }

    public boolean isSameAs(SurveyVersion other) {
        return this.raw.equals(other.raw);
    }

    public String asString() {
        return raw;
    }
}
