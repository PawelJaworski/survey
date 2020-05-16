package pl.javorex.survey.application.adapter;

import pl.javorex.survey.domain.RespondentID;
import pl.javorex.survey.domain.Survey;
import pl.javorex.survey.domain.SurveyRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class SurveyRepositoryInMemoryImpl implements SurveyRepository<String> {
    private Set<Survey> surveys = new HashSet<>();

    @Override
    public Survey save(Survey survey) {
        Optional<Survey> alreadyExisting = surveys.stream()
                .filter(it -> it.hasSameIdentityAs(survey))
                .findAny();

        if (alreadyExisting.isPresent()) {
            surveys.remove(alreadyExisting.get());
        }

        surveys.add(survey);

        return survey;
    }

    @Override
    public Optional<Survey> findByRespondentIDAndType(RespondentID<String> respondentID, String type) {
        return surveys.stream()
                .filter(it -> it.complementedBy(respondentID))
                .filter(it -> it.isTypeOf(type))
                .findAny();
    }
}
