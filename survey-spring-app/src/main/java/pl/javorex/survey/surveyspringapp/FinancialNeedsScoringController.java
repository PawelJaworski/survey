package pl.javorex.survey.surveyspringapp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.javorex.financialneeds.application.FinancialNeedsQueryFacadeImpl;
import pl.javorex.financialneeds.application.query.FinancialNeedsQueryFacade;
import pl.javorex.financialneeds.application.query.ScoringByCustomerID;
import pl.javorex.financialneeds.application.response.ScoringDto;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FinancialNeedsScoringController implements FinancialNeedsQueryFacade<String> {
    private final FinancialNeedsQueryFacadeImpl financialNeedsQueryFacade;

    public FinancialNeedsScoringController(FinancialNeedsQueryFacadeImpl financialNeedsQueryFacade) {
        this.financialNeedsQueryFacade = financialNeedsQueryFacade;
    }

    @Override
    @GetMapping("/financialneeds/scorings/{customerID}")
    public List<ScoringDto> findScoringByCustomerID(@PathVariable String customerID) {
        ScoringByCustomerID<String> query = new ScoringByCustomerID(customerID);

        return financialNeedsQueryFacade.findScoringByCustomerID(query);
    }
}
