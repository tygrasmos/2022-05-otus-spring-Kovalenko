package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptionsModel;
import ru.otus.spring.model.QuestionModel;

import java.util.List;

public interface PrintQuestionsAnswersService {

    void print(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList);

    void printError();
}
