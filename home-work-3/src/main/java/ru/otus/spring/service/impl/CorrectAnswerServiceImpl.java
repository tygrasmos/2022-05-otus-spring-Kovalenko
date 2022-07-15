package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.CorrectAnswer;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.CorrectAnswerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CorrectAnswerServiceImpl implements CorrectAnswerService {

    private static final String questionIdent = "Q";
    private static final String answerCorrectIdent = "C";
    private static final String answerOptionsIdent = "A";


    @Override
    public List<CorrectAnswer> getCorrectAnswers(List<String> reedData
            , List<Question> questionList
            , List<AnswerOptions> answerOptionsList) {
        List<CorrectAnswer> correctAnswerList = new ArrayList<>();
        reedData.forEach(r ->{
            CorrectAnswer correctAnswer = getCorrectAnswer(r, questionList, answerOptionsList);
            if(correctAnswer.getQuestion() != null) {
                correctAnswerList.add(correctAnswer);
            }
        });
        return correctAnswerList;
    }

    private CorrectAnswer getCorrectAnswer(String str, List<Question> questionList, List<AnswerOptions> answerOptionsList){
        CorrectAnswer correctAnswer = new CorrectAnswer();
        StringBuilder sb = new StringBuilder();
        List<String> stringList = Arrays.asList(str.split(" "));
        for(int i = 0; i < stringList.size(); i++){
            String s = stringList.get(i);
            if (i == 0 && s.contains(answerCorrectIdent) && s.contains(questionIdent)){
                int indexQuestion = s.indexOf(questionIdent);
                correctAnswer.setCorrectAnswerIdent(s.substring(0, indexQuestion));
                correctAnswer.setQuestion(findQuestion(questionList, s.substring(indexQuestion)));
            } else if (i == 1 && s.contains(answerOptionsIdent)){
                correctAnswer.setAnswerOptions(findAnswerOptions(answerOptionsList, s));
            } else {
                sb.append(s).append(" ");
            }
        }
        return correctAnswer;
    }

    private Question findQuestion(List<Question> questionList, String questionIdent){
        return questionList.stream()
                .filter(q -> q.getQuestionIdent().equals(questionIdent))
                .findFirst()
                .orElse(null);
    }

    private AnswerOptions findAnswerOptions(List<AnswerOptions> answerOptionsList, String answerOptionsIdent){
        return answerOptionsList.stream()
                .filter(a -> a.getAnswerIdent().equals(answerOptionsIdent))
                .findFirst()
                .orElse(null);
    }
}
