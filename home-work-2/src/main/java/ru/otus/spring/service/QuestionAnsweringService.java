package ru.otus.spring.service;

import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.QuestionModel;

import java.util.List;

public interface QuestionAnsweringService {
    AnswersToTestQuestionsModel getAnswersOnTest(List<QuestionModel> questionModelList);
}
