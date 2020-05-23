package pl.javorex.financialneeds.domain;

import pl.javorex.financialneeds.domain.policy.ScoringCalculationPolicy;

public final class Scoring {
    private final CustomerID customerID;
    private final ScoringType type;
    private ScoringResult result;

    public Scoring(CustomerID customerID, ScoringType type, ScoringResult result) {
        this.customerID = customerID;
        this.type = type;
        this.result = result;
    }

    public boolean concerns(CustomerID customerID) {
        return this.customerID.isSameAs(customerID);
    }

    public boolean hasSameIdentityAs(Scoring other) {
        return other.customerID.isSameAs(customerID);
    }

    public ScoringType getType() {
        return type;
    }

    public ScoringResult getResult() {
        return result;
    }

    public void calculate(ScoringCalculationPolicy scoringCalculationPolicy) {
        result = scoringCalculationPolicy.calculate();
    }
}
