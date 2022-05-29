package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptionsModel;
import ru.otus.spring.model.CorrectAnswerModel;
import ru.otus.spring.model.QuestionModel;
import ru.otus.spring.service.PrintQuestionsAnswersService;

import java.util.List;

@Service
public class PrintQuestionsAnswersServiceImpl implements PrintQuestionsAnswersService {

    @Override
    public void printQuestionsAndAnswerOptions(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList
            , List<CorrectAnswerModel> correctAnswerModelList) {
        System.out.println("-------------------- Questions and Options Answers ------------------------");
        questionModelList.forEach(q -> {
            System.out.println(q.getQuestion());
            answerOptionsModelList.forEach(a -> {
                if (a.getQuestionIdent().equals(q.getQuestionIdent())) {
                    System.out.println("              " + a.getAnswer());
                }
            });
        });
        System.out.println("-------------------- Correct answers to questions ------------------------");
        correctAnswerModelList.forEach(ca ->{
            questionModelList.forEach(q ->{
                if (q.getQuestionIdent().equals(ca.getQuestionIdent())){
                    System.out.println("Question: " + q.getQuestion());
                    answerOptionsModelList.forEach(ao ->{
                        if (ao.getAnswerIdent().equals(ca.getAnswerIdent())){
                            System.out.println("Correct answer: " + ao.getAnswer());
                        }
                    });

                }
            });
        });
        System.out.println("---------------------------------   End -----------------------------------");
    }

    @Override
    public void printTestResult() {

    }


    @Override
    public void printError() {
        System.out.println("Failed to reed data from file.");
    }
}
