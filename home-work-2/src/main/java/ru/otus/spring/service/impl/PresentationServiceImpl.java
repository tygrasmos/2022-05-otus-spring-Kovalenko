package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.*;

import java.util.List;

@Service
public class PresentationServiceImpl implements PresentationService {

    private final ResourceService resourceService;
    private final QuestionService questionService;
    private final AnswersOptionsService answersOptionsService;
    private final PrintQuestionsAnswersService printQuestionsAnswersService;

    public PresentationServiceImpl(ResourceService resourceService,
                                   QuestionService questionService,
                                   AnswersOptionsService answersOptionsService,
                                   PrintQuestionsAnswersService printQuestionsAnswersService){
        this.resourceService = resourceService;
        this.questionService = questionService;
        this.answersOptionsService = answersOptionsService;
        this.printQuestionsAnswersService = printQuestionsAnswersService;
    }

    @Override
    public void presentQuestionsAndAnswersOptions() {
        List<String> reedData = resourceService.reedFile();
        if (reedData != null){
            printQuestionsAnswersService.print(answersOptionsService.getAnswersOptions(reedData)
                    , questionService.getQuestions(reedData));
        } else {
            printQuestionsAnswersService.printError();
        }
    }
}
