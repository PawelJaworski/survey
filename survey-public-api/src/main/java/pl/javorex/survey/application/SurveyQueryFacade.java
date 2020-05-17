package pl.javorex.survey.application;

import pl.javorex.survey.application.query.SurveyByRespondentAndTypeQuery;
import pl.javorex.survey.application.query.SurveyByTypeAndVersionAndRespondentQuery;
import pl.javorex.survey.application.response.SurveyDto;

public interface SurveyQueryFacade {
  default SurveyDto findSurveyByRespondentIDAndType(SurveyByTypeAndVersionAndRespondentQuery query) {
    throw new UnsupportedOperationException();
  }
}
