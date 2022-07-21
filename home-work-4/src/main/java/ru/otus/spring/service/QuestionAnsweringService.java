package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.Question;

import java.util.List;

public interface QuestionAnsweringService {

    AnswersToTestQuestionsModel getAnswersOnTest(List<Question> questionList
            , List<AnswerOptions> answerOptionsList);

}
