package pl.javorex.survey.surveyspringapp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.javorex.financialneeds.application.FinancialNeedsQueryFacadeImpl;
import pl.javorex.financialneeds.application.query.FinancialNeedsQueryFacade;
import pl.javorex.financialneeds.application.query.ScoringByCustomerID;
import pl.javorex.financialneeds.application.dto.ScoringDto;
import pl.javorex.financialneeds.application.response.ScoringRes;
import pl.javorex.financialneeds.application.response.ScoringResponseMapper;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FinancialNeedsScoringController implements FinancialNeedsQueryFacade<String> {
    private final FinancialNeedsQueryFacadeImpl financialNeedsQueryFacade;
    private final ScoringResponseMapper scoringResponseMapper;

    public FinancialNeedsScoringController(FinancialNeedsQueryFacadeImpl financialNeedsQueryFacade) {
        this.financialNeedsQueryFacade = financialNeedsQueryFacade;
        this.scoringResponseMapper = new ScoringResponseMapper();
    }

    @Override
    @GetMapping("/financialneeds/scorings/{customerID}")
    public List<ScoringRes> findScoringByCustomerID(@PathVariable String customerID) {
        ScoringByCustomerID<String> query = new ScoringByCustomerID(customerID);

        return financialNeedsQueryFacade.findScoringByCustomerID(query).stream()
                .map(scoringResponseMapper::map)
                .collect(Collectors.toList());
    }
}
