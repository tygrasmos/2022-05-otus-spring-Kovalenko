package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.*;

import java.util.List;

@Service
public class PresentationServiceImpl implements PresentationService {

    private final ResourceService resourceService;
    private final QuestionService questionService;
    private final AnswersOptionsService answersOptionsService;
    private final PrintService printService;
    private final CorrectAnswerService correctAnswerService;

    public PresentationServiceImpl(ResourceService resourceService,
                                   QuestionService questionService,
                                   AnswersOptionsService answersOptionsService,
                                   PrintService printService,
                                   CorrectAnswerService correctAnswerService){
        this.resourceService = resourceService;
        this.questionService = questionService;
        this.answersOptionsService = answersOptionsService;
        this.printService = printService;
        this.correctAnswerService = correctAnswerService;
    }

    @Override
    public void presentQuestionsAndAnswersOptions() {
        List<String> reedData = resourceService.reedFile();
        if (reedData != null){
            printService.printQuestionsAndAnswerOptionsAndCorrectAnswers(
                    answersOptionsService.getAnswersOptions(reedData),
                    questionService.getQuestions(reedData),
                    correctAnswerService.getCorrectAnswers(reedData));
        } else {
            printService.printError();
        }
    }
}
