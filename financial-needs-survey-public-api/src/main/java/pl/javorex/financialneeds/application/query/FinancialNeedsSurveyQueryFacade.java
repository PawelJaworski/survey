package pl.javorex.financialneeds.application.query;

import pl.javorex.financialneeds.application.request.SurveyAnswerReq;
import pl.javorex.financialneeds.application.response.SurveyRes;

public interface FinancialNeedsSurveyQueryFacade {
    SurveyRes getSurvey(String respondentID);
    void answerSurvey(String respondentID, SurveyAnswerReq answerRequest);
}
