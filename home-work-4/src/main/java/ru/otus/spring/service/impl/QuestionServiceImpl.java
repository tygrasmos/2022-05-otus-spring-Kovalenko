package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.QuestionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final String questionIdent = "Q";
    private static final String answerOptionsIdent = "A";
    private static final String answerCorrectIdent = "C";

    @Override
    public List<Question> getQuestions(List<String> reedData) {
        List<Question> questionList = new ArrayList<>();
        reedData.forEach(r ->{
            Question question = getQuestion(r);
            if(question.getQuestionIdent() != null) {
                questionList.add(question);
            }
        });
        return questionList;
    }

    private Question getQuestion(String str){
        Question question = new Question();
        StringBuilder sb = new StringBuilder();
        List<String> stringList = Arrays.asList(str.split(" "));
        for(int i = 0; i < stringList.size(); i++){
            String s = stringList.get(i);
            if (i == 0 && s.contains(questionIdent)
                    && !s.contains(answerOptionsIdent)
                    && !s.contains(answerCorrectIdent)){
                question.setQuestionIdent(stringList.get(i));
            } else {
                sb.append(s).append(" ");
            }
        }
        question.setQuestion(sb.substring(0, sb.length() - 1));
        return question;
    }
}
