package pl.javorex.financialneeds.application.response;

public class ScoringDto {
    private final String type;
    private final String result;

    public ScoringDto(String type, String result) {
        this.type = type;
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public String getResult() {
        return result;
    }
}
