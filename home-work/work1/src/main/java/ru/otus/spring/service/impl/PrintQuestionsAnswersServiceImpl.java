package ru.otus.spring.service.impl;

import ru.otus.spring.service.AnswersOptionsService;
import ru.otus.spring.service.PrintQuestionsAnswersService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.ResourceService;

import java.util.List;

public class PrintQuestionsAnswersServiceImpl implements PrintQuestionsAnswersService {

    private final ResourceService resourceService;
    private final QuestionService questionService;
    private final AnswersOptionsService answersOptionsService;

    public PrintQuestionsAnswersServiceImpl(ResourceService resourceService,
                                            QuestionService questionService,
                                            AnswersOptionsService answersOptionsService){
        this.resourceService = resourceService;
        this.questionService = questionService;
        this.answersOptionsService = answersOptionsService;
    }

    public void print() {
        List<String> reedData = resourceService.reedFile();
        if (reedData != null) {
            System.out.println("------------------- Questions and Options Answers ------------------------");
            questionService.getQuestions(reedData).forEach(q -> {
                System.out.println(q.getQuestion());
                answersOptionsService.getAnswersOptions(reedData).forEach(a -> {
                    if (a.getQuestionIdent().equals(q.getQuestionIdent())) {
                        System.out.println("              " + a.getAnswer());
                    }
                });
            });
            System.out.println("---------------------------------   End ------------------------------------");
        } else {
            System.out.println("Failed to reed data from file.");
        }
    }
}
