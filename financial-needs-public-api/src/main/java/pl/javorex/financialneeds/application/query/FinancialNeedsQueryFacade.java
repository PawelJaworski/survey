package pl.javorex.financialneeds.application.query;

import pl.javorex.financialneeds.application.response.ScoringRes;

import java.util.List;

public interface FinancialNeedsQueryFacade<C> {
    List<ScoringRes> findScoringByCustomerID(C customerID);
}
