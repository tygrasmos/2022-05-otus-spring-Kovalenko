package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.CorrectAnswer;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.TestResult;

import java.util.List;

public interface PrintService {

    void printQuestionsAndAnswerOptionsAndCorrectAnswers(List<AnswerOptions> answerOptionsList
            , List<Question> questionList
            , List<CorrectAnswer> correctAnswerList);

    void printQuestions(List<Question> questionList);

    void printQuestionsAndAnswerOptions(List<AnswerOptions> answerOptionsList
            , List<Question> questionList);

    void printTestResult(TestResult testResult);

    void printQuestionsAndCorrectAnswers(List<AnswerOptions> answerOptionsList
            , List<Question> questionList
            , List<CorrectAnswer> correctAnswerList);

    void printError();

    void printSingleQuestionAndAnswers(Question question, List<AnswerOptions> answerOptionsList);

    void print(Object o);

}
