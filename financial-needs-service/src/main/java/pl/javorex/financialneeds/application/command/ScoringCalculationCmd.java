package pl.javorex.financialneeds.application.command;

import java.util.Map;

public final class ScoringCalculationCmd {
    private final Object customerID;
    private final Map<String, String> answerByQuestion;

    public ScoringCalculationCmd(Object customerID, Map<String, String> answerByQuestion) {
        this.customerID = customerID;
        this.answerByQuestion = answerByQuestion;
    }

    public Object getCustomerID() {
        return customerID;
    }

    public Map<String, String> getAnswerByQuestion() {
        return answerByQuestion;
    }
}
