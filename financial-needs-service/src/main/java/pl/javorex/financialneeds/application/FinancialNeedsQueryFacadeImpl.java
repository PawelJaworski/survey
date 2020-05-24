package pl.javorex.financialneeds.application;

import pl.javorex.financialneeds.application.query.ScoringByCustomerID;
import pl.javorex.financialneeds.application.dto.ScoringDto;
import pl.javorex.financialneeds.domain.CustomerID;
import pl.javorex.financialneeds.domain.ScoringRepository;

import java.util.List;
import java.util.stream.Collectors;

public final class FinancialNeedsQueryFacadeImpl {
    private final ScoringRepository scoringRepository;

    public FinancialNeedsQueryFacadeImpl(ScoringRepository scoringRepository) {
        this.scoringRepository = scoringRepository;
    }

    public List<ScoringDto> findScoringByCustomerID(ScoringByCustomerID query) {
        CustomerID customerID = CustomerID.customerIDOf(query.raw);
        return scoringRepository.findByCustomerID(customerID).stream()
                .map(it -> new ScoringDto(it.getType().name(), it.getResult().name()))
                .collect(Collectors.toList());
    }
}
