package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptionsModel;
import ru.otus.spring.model.QuestionModel;
import ru.otus.spring.service.PrintQuestionsAnswersService;

import java.util.List;

@Service
public class PrintQuestionsAnswersServiceImpl implements PrintQuestionsAnswersService {

    @Override
    public void print(List<AnswerOptionsModel> answerOptionsModelList, List<QuestionModel> questionModelList) {
        System.out.println("------------------- Questions and Options Answers ------------------------");
        questionModelList.forEach(q -> {
            System.out.println(q.getQuestion());
            answerOptionsModelList.forEach(a -> {
                if (a.getQuestionIdent().equals(q.getQuestionIdent())) {
                    System.out.println("              " + a.getAnswer());
                }
            });
        });
        System.out.println("---------------------------------   End ------------------------------------");
    }

    @Override
    public void printError() {
        System.out.println("Failed to reed data from file.");
    }
}
