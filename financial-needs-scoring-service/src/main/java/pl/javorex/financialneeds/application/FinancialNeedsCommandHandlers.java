package pl.javorex.financialneeds.application;

import pl.javorex.financialneeds.application.command.ScoringCalculationCmd;
import pl.javorex.financialneeds.domain.*;
import pl.javorex.financialneeds.domain.policy.ScoringScoringCalculationIkePolicy;

import java.util.Map;

public final class FinancialNeedsCommandHandlers {
    private final ScoringRepository scoringRepository;

    public FinancialNeedsCommandHandlers(ScoringRepository scoringRepository) {
        this.scoringRepository = scoringRepository;
    }

    public void calculateScoring(ScoringCalculationCmd cmd) {
        CustomerID customerID = CustomerID.customerIDOf(cmd.getCustomerID());
        Scoring scoringIke = scoringRepository.findByCustomerID(customerID).stream()
                .filter(it -> it.getType().name().equals("IKE"))
                .findFirst()
                .orElseGet(() -> new Scoring(customerID, ScoringType.IKE, ScoringResult.NONE));

        Map<String, String> answerByCode = cmd.getAnswerByQuestion();
        scoringIke.calculate(new ScoringScoringCalculationIkePolicy(answerByCode));

        scoringRepository.save(scoringIke);
    }
}
