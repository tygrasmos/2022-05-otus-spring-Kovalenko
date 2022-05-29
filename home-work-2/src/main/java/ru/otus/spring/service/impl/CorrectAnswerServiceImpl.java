package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.CorrectAnswerModel;
import ru.otus.spring.service.CorrectAnswerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CorrectAnswerServiceImpl implements CorrectAnswerService {

    private static final String questionIdent = "Q";
    private static final String answerCorrectIdent = "C";


    @Override
    public List<CorrectAnswerModel> getCorrectAnswers(List<String> reedData) {
        List<CorrectAnswerModel> correctAnswerModelList = new ArrayList<>();
        reedData.forEach(r ->{
            CorrectAnswerModel correctAnswerModel = getCorrectAnswer(r);
            if(correctAnswerModel.getQuestionIdent() != null) {
                correctAnswerModelList.add(correctAnswerModel);
            }
        });
        return correctAnswerModelList;
    }

    private CorrectAnswerModel getCorrectAnswer(String str){
        CorrectAnswerModel correctAnswerModel = new CorrectAnswerModel();
        StringBuilder sb = new StringBuilder();
        List<String> stringList = Arrays.asList(str.split(" "));
        for(int i = 0; i < stringList.size(); i++){
            String s = stringList.get(i);
            if (i == 0 && s.contains(answerCorrectIdent) && s.contains(questionIdent)){
                correctAnswerModel.setQuestionIdent(stringList.get(i));
            } else {
                sb.append(s).append(" ");
            }
        }
        correctAnswerModel.setQuestionIdent(sb.substring(0, sb.length() - 1));
        return correctAnswerModel;
    }

}
