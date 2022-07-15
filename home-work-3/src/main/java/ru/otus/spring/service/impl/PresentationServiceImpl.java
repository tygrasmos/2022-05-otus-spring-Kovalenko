package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.TestResult;
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
        List<Question> questionList = questionService.getQuestions(reedData);
        List<AnswerOptions> answerOptionsList = answersOptionsService.getAnswersOptions(reedData);
        if (reedData != null){
            AnswersToTestQuestionsModel model =  questionAnsweringService.getAnswersOnTest(questionList, answerOptionsList);
            TestResult testResult = studentTestResultService.getTestResult(
                    model, correctAnswerService.getCorrectAnswers(reedData, questionList, answerOptionsList));
            printService.printTestResult(testResult);
        } else {
            printService.printError();
        }
    }
}
