package pl.javorex.financialneeds.application.command;

import pl.javorex.survey.application.command.AnswerCmd;

import java.util.Set;

public final class FinancialNeedsAnswerCmd {
    private final Object respondentID;
    private final Set<AnswerCmd> answers;

    public FinancialNeedsAnswerCmd(Object respondentID, Set<AnswerCmd> answers) {
        this.respondentID = respondentID;
        this.answers = answers;
    }

    public Object getRespondentID() {
        return respondentID;
    }

    public Set<AnswerCmd> getAnswers() {
        return answers;
    }
}
