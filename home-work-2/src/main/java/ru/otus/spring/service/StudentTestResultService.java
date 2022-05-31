package ru.otus.spring.service;

import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.TestResultModel;

public interface StudentTestResultService {

    TestResultModel getTestResult(AnswersToTestQuestionsModel answersToTestQuestionsModel);
}
