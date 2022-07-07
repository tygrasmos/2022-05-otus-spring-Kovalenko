package ru.otus.spring.service;

import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.CorrectAnswer;
import ru.otus.spring.model.TestResult;

import java.util.List;

public interface StudentTestResultService {

    TestResult getTestResult(AnswersToTestQuestionsModel answersToTestQuestionsModel
            , List<CorrectAnswer> correctAnswerList);
}
