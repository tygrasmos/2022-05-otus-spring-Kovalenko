package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.TestResultModel;
import ru.otus.spring.service.*;

import java.util.List;

@Service
public class PresentationServiceImpl implements PresentationService {

    private final ResourceService resourceService;
    private final QuestionService questionService;
    private final AnswersOptionsService answersOptionsService;
    private final PrintService printService;
    private final CorrectAnswerService correctAnswerService;
    private final QuestionAnsweringService questionAnsweringService;
    private final StudentTestResultService studentTestResultService;

    public PresentationServiceImpl(ResourceService resourceService,
                                   QuestionService questionService,
                                   AnswersOptionsService answersOptionsService,
                                   PrintService printService,
                                   CorrectAnswerService correctAnswerService,
                                   QuestionAnsweringService questionAnsweringService,
                                   StudentTestResultService studentTestResultService){
        this.resourceService = resourceService;
        this.questionService = questionService;
        this.answersOptionsService = answersOptionsService;
        this.printService = printService;
        this.correctAnswerService = correctAnswerService;
        this.questionAnsweringService = questionAnsweringService;
        this.studentTestResultService = studentTestResultService;
    }

    @Override
    public void presentQuestionsAndAnswersOptions() {
        List<String> reedData = resourceService.reedFile();
        if (reedData != null){
            AnswersToTestQuestionsModel model =  questionAnsweringService.getAnswersOnTest(questionService.getQuestions(reedData)
                    , answersOptionsService.getAnswersOptions(reedData));
            TestResultModel testResult = studentTestResultService.getTestResult(model, correctAnswerService.getCorrectAnswers(reedData));
            printService.printTestResult(testResult);
        } else {
            printService.printError();
        }
    }
}
