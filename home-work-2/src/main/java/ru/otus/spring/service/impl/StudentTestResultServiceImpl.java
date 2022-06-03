package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOnQuestionModel;
import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.CorrectAnswerModel;
import ru.otus.spring.model.TestResultModel;
import ru.otus.spring.service.StudentTestResultService;

import java.util.List;

@Service
public class StudentTestResultServiceImpl implements StudentTestResultService {

    @Value("${quantityCorrectAnswers}")
    private String quantityCorrectAnswers;

    @Override
    public TestResultModel getTestResult(AnswersToTestQuestionsModel answersToTestQuestionsModel
            , List<CorrectAnswerModel> correctAnswerModelList) {
        TestResultModel testResultModel = new TestResultModel();
        testResultModel.setAnswersToTestQuestionsModel(answersToTestQuestionsModel);

        Integer quantityCorrectAnswers  = getQuantityCorrectAnswers(answersToTestQuestionsModel, correctAnswerModelList);
        Integer quantityQuestions = answersToTestQuestionsModel.getQuestionResultModelList().size();

        testResultModel.setQuestionsQuantity(quantityQuestions);
        testResultModel.setCorrectAnswersQuantity(quantityCorrectAnswers);
        testResultModel.setTestScore(getScore(quantityCorrectAnswers, quantityQuestions));
        testResultModel.setIsOffset(isOffset(quantityCorrectAnswers));
        return testResultModel;
    }

    private Integer getQuantityCorrectAnswers(AnswersToTestQuestionsModel answersToTestQuestionsModel,
                                              List<CorrectAnswerModel> correctAnswerModelList){
        int count = 0;
        for(CorrectAnswerModel a : correctAnswerModelList){
            for(AnswerOnQuestionModel q : answersToTestQuestionsModel.getQuestionResultModelList()){
                if (a.getQuestionIdent().equals(q.getQuestionIdent())
                        && a.getAnswerIdent().equals(q.getAnswerIdent())){
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
