package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptionsModel;
import ru.otus.spring.service.AnswersOptionsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnswerOptionsServiceImpl implements AnswersOptionsService {

    private static final String questionIdent = "Q";
    private static final String answerOptionsIdent = "A";
    private static final String answerCorrectIdent = "C";

    @Override
    public List<AnswerOptionsModel> getAnswersOptions(List<String> reedData) {
        List<AnswerOptionsModel> answerOptionsModelList = new ArrayList<>();
        reedData.forEach(r ->{
            AnswerOptionsModel answerOptionsModel = getAnswer(r);
            if(answerOptionsModel.getQuestionIdent() != null) {
                answerOptionsModelList.add(answerOptionsModel);
            }
        });
        return answerOptionsModelList;
    }

    private AnswerOptionsModel getAnswer(String str){
        AnswerOptionsModel answerOptionsModel = new AnswerOptionsModel();
        StringBuilder sb = new StringBuilder();
        List<String> stringList = Arrays.asList(str.split(" "));
        for(int i = 0; i < stringList.size(); i++){
            String s = stringList.get(i);
            if (i == 0 && s.contains(questionIdent)
                    && s.contains(answerOptionsIdent)
                    && !s.contains(answerCorrectIdent)){
                int indexQuestion = s.indexOf(questionIdent);
                answerOptionsModel.setAnswerIdent(s.substring(0, indexQuestion));
                answerOptionsModel.setQuestionIdent(s.substring(indexQuestion));
            } else {
                sb.append(s).append(" ");
            }
        }
        answerOptionsModel.setAnswer(sb.substring(0, sb.length() - 1));
        return answerOptionsModel;
    }
}
