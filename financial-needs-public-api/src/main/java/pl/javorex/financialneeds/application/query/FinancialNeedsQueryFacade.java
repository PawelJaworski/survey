package pl.javorex.financialneeds.application.query;

import pl.javorex.financialneeds.application.response.ScoringDto;

import java.util.List;

public interface FinancialNeedsQueryFacade<C> {
    List<ScoringDto> findScoringByCustomerID(C customerID);
}
