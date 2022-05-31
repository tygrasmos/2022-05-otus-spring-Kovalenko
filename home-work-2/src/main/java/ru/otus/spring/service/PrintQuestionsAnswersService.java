package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptionsModel;
import ru.otus.spring.model.CorrectAnswerModel;
import ru.otus.spring.model.QuestionModel;
import ru.otus.spring.model.TestResultModel;

import java.util.List;

public interface PrintQuestionsAnswersService {

    void printQuestionsAndAnswerOptionsAndCorrectAnswers(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList, List<CorrectAnswerModel> correctAnswerModelList);

    void printTestResult(TestResultModel testResultModel);

    void printError();
}
