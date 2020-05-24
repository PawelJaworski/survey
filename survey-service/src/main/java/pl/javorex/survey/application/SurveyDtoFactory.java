package pl.javorex.survey.application;


import pl.javorex.survey.application.dto.AnsweredQuestionDto;
import pl.javorex.survey.application.dto.QuestionDefinitionDto;
import pl.javorex.survey.application.dto.SurveyDto;
import pl.javorex.survey.domain.Survey;
import pl.javorex.survey.domain.surveydefinition.QuestionDefinition;
import pl.javorex.survey.domain.surveydefinition.SurveyDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class SurveyDtoFactory {
  SurveyDtoFactory() {}

  SurveyDto fromSurvey(Survey survey) {
    SurveyDefinition definition = survey.getDefinition();
    List<QuestionDefinitionDto> questionDefinitionDtos = definition.getQuestions().stream()
      .map(this::assemble)
      .collect(Collectors.toList());

    List<AnsweredQuestionDto> answeredQuestions = survey.getAnsweredQuestions().stream()
      .map(it -> new AnsweredQuestionDto(it.getQuestionCode(), it.getAnswerCode(), it.getAnswerText()))
      .collect(Collectors.toList());

    return new SurveyDto(questionDefinitionDtos, answeredQuestions);
  }

  SurveyDto empty(SurveyDefinition surveyDefinition) {
    List<QuestionDefinitionDto> questionDefinitionDtos = surveyDefinition
            .getQuestions().stream()
            .map(this::assemble)
            .collect(Collectors.toList());

    return new SurveyDto(questionDefinitionDtos, new ArrayList<>());
  }

  QuestionDefinitionDto assemble(QuestionDefinition questionDefinition) {
    String questionCode = questionDefinition.getQuestionCode();
    List<String> possibleAnswers = questionDefinition.getPossibleAnswers();

    return new QuestionDefinitionDto(questionCode, possibleAnswers);
  }
}
