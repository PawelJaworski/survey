package pl.javorex.financialneeds.domain.policy;

import pl.javorex.financialneeds.domain.ScoringResult;

public interface ScoringCalculationPolicy {
    ScoringResult calculate();
}
