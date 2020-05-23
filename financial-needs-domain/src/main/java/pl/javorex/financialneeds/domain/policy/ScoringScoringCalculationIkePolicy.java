package pl.javorex.financialneeds.domain.policy;

import pl.javorex.financialneeds.domain.ScoringResult;

import java.util.Map;

public class ScoringScoringCalculationIkePolicy implements ScoringCalculationPolicy {
    private final Map<String, String> answerByCode;

    public ScoringScoringCalculationIkePolicy(Map<String, String> answerByCode) {
        this.answerByCode = answerByCode;
    }

    @Override
    public ScoringResult calculate() {
        if (answerByCode.get("SOURCE_OF_INCOME").equals("GAMESTER")) {
            return ScoringResult.UNRECOMMENDED;
        }
        return ScoringResult.RECOMMENDED;
    }
}
