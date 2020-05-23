package pl.javorex.financialneeds.application;

import pl.javorex.financialneeds.application.adapter.ScoringRepositoryInMemoryImpl;
import pl.javorex.financialneeds.domain.ScoringRepository;

public final class FinancialNeedsServiceConfig {
    private final ScoringRepository scoringRepository;

    public FinancialNeedsServiceConfig() {
        this(new ScoringRepositoryInMemoryImpl());
    }

    public FinancialNeedsServiceConfig(ScoringRepository scoringRepository) {
        this.scoringRepository = scoringRepository;
    }

    public FinancialNeedsQueryFacadeImpl financialNeedsQueryFacade() {
        return new FinancialNeedsQueryFacadeImpl(scoringRepository);
    }

    public FinancialNeedsCommandHandlers financialNeedsCommandHandlers() {
        return new FinancialNeedsCommandHandlers(scoringRepository);
    }
}
