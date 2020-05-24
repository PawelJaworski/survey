package pl.javorex.financialneeds.application.response;

public class ScoringRes {
    private String type;
    private String result;

    private ScoringRes(){}

    public ScoringRes(String type, String result) {
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
