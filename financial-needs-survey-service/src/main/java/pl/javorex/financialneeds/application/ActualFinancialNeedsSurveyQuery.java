package pl.javorex.financialneeds.application;

public final class ActualFinancialNeedsSurveyQuery<R> {
    private final R respondentID;

    public ActualFinancialNeedsSurveyQuery(R respondentID) {
        this.respondentID = respondentID;
    }

    public R getRespondentID() {
        return respondentID;
    }
}
