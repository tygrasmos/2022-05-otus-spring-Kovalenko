package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptionsModel;
import ru.otus.spring.model.CorrectAnswerModel;
import ru.otus.spring.model.QuestionModel;
import ru.otus.spring.model.TestResultModel;

import java.util.List;

public interface PrintService {

    void printQuestionsAndAnswerOptionsAndCorrectAnswers(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList
            , List<CorrectAnswerModel> correctAnswerModelList);

    void printQuestions(List<QuestionModel> questionModelList);

    void printQuestionsAndAnswerOptions(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList);

    void printTestResult(TestResultModel testResultModel);

    void printQuestionsAndCorrectAnswers(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList
            , List<CorrectAnswerModel> correctAnswerModelList);

    void printError();

    void printSingleQuestionAndAnswers(QuestionModel question, List<AnswerOptionsModel> answerOptionsList);

    void print(Object o);
}
