package pl.javorex.financialneeds.application;

import pl.javorex.financialneeds.application.adapter.ScoringRepositoryInMemoryImpl;
import pl.javorex.financialneeds.domain.ScoringRepository;

public final class FinancialNeedsServiceConfig {
    private final FinancialNeedsQueryFacadeImpl financialNeedsQueryHandler;
    private final FinancialNeedsCommandHandlers financialNeedsCommandHandlers;

    public FinancialNeedsServiceConfig() {
        this(new ScoringRepositoryInMemoryImpl());
    }

    public FinancialNeedsServiceConfig(ScoringRepository scoringRepository) {
        this.financialNeedsQueryHandler = new FinancialNeedsQueryFacadeImpl(scoringRepository);
        this.financialNeedsCommandHandlers = new FinancialNeedsCommandHandlers(scoringRepository);
    }

    public FinancialNeedsQueryFacadeImpl financialNeedsQueryFacade() {
        return financialNeedsQueryHandler;
    }

    public FinancialNeedsCommandHandlers financialNeedsCommandHandlers() {
        return financialNeedsCommandHandlers;
    }
}
