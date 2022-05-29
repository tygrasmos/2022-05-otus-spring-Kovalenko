package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptionsModel;
import ru.otus.spring.model.CorrectAnswerModel;
import ru.otus.spring.model.QuestionModel;

import java.util.List;

public interface PrintQuestionsAnswersService {

    void printQuestionsAndAnswerOptions(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList, List<CorrectAnswerModel> correctAnswerModelList);

    void printTestResult();

    void printError();
}
