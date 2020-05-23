package pl.javorex.survey.application.event;

import pl.javorex.survey.application.response.AnsweredQuestionDto;

import java.util.List;

public final class SurveyAnsweredEvent<R> {
    private final R respondentID;
    private final String surveyType;
    private final String surveyVersion;
    private final List<AnsweredQuestionDto> answeredQuestions;

    public SurveyAnsweredEvent(R respondentID, String surveyType, String surveyVersion,
                               List<AnsweredQuestionDto> answeredQuestions) {
        this.respondentID = respondentID;
        this.surveyType = surveyType;
        this.surveyVersion = surveyVersion;
        this.answeredQuestions = answeredQuestions;
    }

    public R getRespondentID() {
        return respondentID;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public String getSurveyVersion() {
        return surveyVersion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("respondentID", respondentID)
                .append("surveyType", surveyType)
                .append("surveyVersion", surveyVersion)
                .toString();
    }
}
