package pl.javorex.financialneeds.domain;

import java.util.List;

public interface ScoringRepository {
    Scoring save(Scoring scoring);
    List<Scoring> findByCustomerID(CustomerID customerID);
}
