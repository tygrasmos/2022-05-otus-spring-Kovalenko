package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOnQuestion;
import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.CorrectAnswer;
import ru.otus.spring.model.TestResult;
import ru.otus.spring.service.StudentTestResultService;

import java.util.List;

@Service
public class StudentTestResultServiceImpl implements StudentTestResultService {

    @Value("${quantityCorrectAnswers}")
    private String quantityCorrectAnswers;

    @Override
    public TestResult getTestResult(AnswersToTestQuestionsModel answersToTestQuestionsModel
            , List<CorrectAnswer> correctAnswerList) {
        TestResult testResult = new TestResult();
        testResult.setAnswersToTestQuestionsModel(answersToTestQuestionsModel);

        Integer quantityCorrectAnswers  = getQuantityCorrectAnswers(answersToTestQuestionsModel, correctAnswerList);
        Integer quantityQuestions = answersToTestQuestionsModel.getQuestionResultModelList().size();

        testResult.setQuestionsQuantity(quantityQuestions);
        testResult.setCorrectAnswersQuantity(quantityCorrectAnswers);
        testResult.setTestScore(getScore(quantityCorrectAnswers, quantityQuestions));
        testResult.setIsOffset(isOffset(quantityCorrectAnswers));
        return testResult;
    }

    private Integer getQuantityCorrectAnswers(AnswersToTestQuestionsModel answersToTestQuestionsModel,
                                              List<CorrectAnswer> correctAnswerList){
        int count = 0;
        for(CorrectAnswer a : correctAnswerList){
            for(AnswerOnQuestion q : answersToTestQuestionsModel.getQuestionResultModelList()){
                if (a.getQuestion().equals(q.getQuestion())
                        && a.getAnswerOptions().equals(q.getAnswerOptions())){
                    count++;
                }
            }
        }
        return count;
    }

    private Boolean isOffset(Integer qa){
        return qa >= Integer.parseInt(quantityCorrectAnswers);
    }

    private Integer getScore(Integer qa, Integer qq){
        int res = qq - qa;
        if (res == 0){
            return 5;
        } else if (res == 1){
            return 4;
        } else if (res == 2){
            return 3;
        } else {
            return 2;
        }
    }
}
