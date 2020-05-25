package pl.javorex.financialneeds.application.adapter;

import pl.javorex.financialneeds.domain.CustomerID;
import pl.javorex.financialneeds.domain.Scoring;
import pl.javorex.financialneeds.domain.ScoringRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ScoringRepositoryInMemoryImpl implements ScoringRepository {
    private final Set<Scoring> scorings = new HashSet<>();

    @Override
    public Scoring save(Scoring scoring) {
        Optional<Scoring> alreadyExisting = scorings.stream()
                .filter(it -> it.hasSameIdentityAs(scoring))
                .findAny();

        if (alreadyExisting.isPresent()) {
            scorings.remove(alreadyExisting.get());
        }

        scorings.add(scoring);

        return scoring;
    }

    @Override
    public List<Scoring> findByCustomerID(CustomerID customerID) {
        return scorings.stream()
                .filter(it -> it.concerns(customerID))
                .collect(Collectors.toList());
    }
}
