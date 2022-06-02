package ru.otus.spring.service;

import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.CorrectAnswerModel;
import ru.otus.spring.model.TestResultModel;

import java.util.List;

public interface StudentTestResultService {

    TestResultModel getTestResult(AnswersToTestQuestionsModel answersToTestQuestionsModel
            , List<CorrectAnswerModel> correctAnswerModelList);
}
