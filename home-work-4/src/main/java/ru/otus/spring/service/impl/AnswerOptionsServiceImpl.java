package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptions;
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
    public List<AnswerOptions> getAnswersOptions(List<String> reedData) {
        List<AnswerOptions> answerOptionsList = new ArrayList<>();
        reedData.forEach(r ->{
            AnswerOptions answerOptions = getAnswer(r);
            if(answerOptions.getQuestionIdent() != null) {
                answerOptionsList.add(answerOptions);
            }
        });
        return answerOptionsList;
    }

    private AnswerOptions getAnswer(String str){
        AnswerOptions answerOptions = new AnswerOptions();
        StringBuilder sb = new StringBuilder();
        List<String> stringList = Arrays.asList(str.split(" "));
        for(int i = 0; i < stringList.size(); i++){
            String s = stringList.get(i);
            if (i == 0 && s.contains(questionIdent)
                    && s.contains(answerOptionsIdent)
                    && !s.contains(answerCorrectIdent)){
                int indexQuestion = s.indexOf(questionIdent);
                answerOptions.setAnswerIdent(s.substring(0, indexQuestion));
                answerOptions.setQuestionIdent(s.substring(indexQuestion));
            } else {
                sb.append(s).append(" ");
            }
        }
        answerOptions.setAnswer(sb.substring(0, sb.length() - 1));
        return answerOptions;
    }
}
