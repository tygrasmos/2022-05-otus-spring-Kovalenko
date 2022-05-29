package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.QuestionModel;
import ru.otus.spring.service.QuestionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final String questionIdent = "Q";
    private static final String answerIdent = "A";

    @Override
    public List<QuestionModel> getQuestions(List<String> reedData) {
        List<QuestionModel> questionModelList = new ArrayList<>();
        reedData.forEach(r ->{
            QuestionModel questionModel = getQuestion(r);
            if(questionModel.getQuestionIdent() != null) {
                questionModelList.add(questionModel);
            }
        });
        return questionModelList;
    }

    private QuestionModel getQuestion(String str){
        QuestionModel questionModel = new QuestionModel();
        StringBuilder sb = new StringBuilder();
        List<String> stringList = Arrays.asList(str.split(" "));
        for(int i = 0; i < stringList.size(); i++){
            String s = stringList.get(i);
            if (i == 0 && s.contains(questionIdent) && !s.contains(answerIdent)){
                questionModel.setQuestionIdent(stringList.get(i));
            } else {
                sb.append(s).append(" ");
            }
        }
        questionModel.setQuestion(sb.substring(0, sb.length() - 1));
        return questionModel;
    }
}
