package pl.javorex.survey.application;

import pl.javorex.survey.application.command.AnswerCmd;
import pl.javorex.survey.domain.AnsweredQuestion;

final class AnsweredQuestionAssembler {
    AnsweredQuestion assemble(String questionCode, AnswerCmd cmd) {
        return new AnsweredQuestion( questionCode, cmd.answerCode, cmd.answerText );
    }
}
