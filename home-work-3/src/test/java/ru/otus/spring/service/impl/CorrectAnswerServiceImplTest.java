package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.CorrectAnswer;
import ru.otus.spring.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Class CorrectAnswerServiceImpl")
@SpringBootTest
public class CorrectAnswerServiceImplTest {

    private final String [] testStringArray = {"C1Q1 A2", "Q1 Question?", "A2Q1 Answer2", "C2Q2 A3", "Q2 Question?", "A3Q2 Answer3"};

    @DisplayName("Correct execution of the method Get a list of correct answer.")
    @Test
    void mustBeReceivedAValidCorrectAnswerList(){
        CorrectAnswerServiceImpl correctAnswerService = new CorrectAnswerServiceImpl();
        AnswerOptionsServiceImpl answerOptionsService = new AnswerOptionsServiceImpl();
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        Assertions.assertEquals(getTestAnswerOptionsModelList()
                , correctAnswerService.getCorrectAnswers(Arrays.asList(testStringArray),
                        questionService.getQuestions(Arrays.asList(testStringArray)),
                        answerOptionsService.getAnswersOptions(Arrays.asList(testStringArray))));
    }

    private List<CorrectAnswer> getTestAnswerOptionsModelList(){
        List<CorrectAnswer> list = new ArrayList<>();
        CorrectAnswer correctAnswer1 = new CorrectAnswer();
        correctAnswer1.setAnswerOptions(getAnswerOptions("Answer2", "A2", "Q1"));//setAnswerIdent("A1");
        correctAnswer1.setQuestion(getQuestion("Question?", "Q1"));//setQuestionIdent("Q1");
        correctAnswer1.setCorrectAnswerIdent("C1");
        CorrectAnswer correctAnswer2 = new CorrectAnswer();
        correctAnswer2.setAnswerOptions(getAnswerOptions("Answer3", "A3", "Q2"));//setAnswerIdent("A3");
        correctAnswer2.setQuestion(getQuestion("Question?", "Q2"));//setQuestionIdent("Q2");
        correctAnswer2.setCorrectAnswerIdent("C2");
        list.add(correctAnswer1);
        list.add(correctAnswer2);
        return list;
    }

    private Question getQuestion(String question, String questionIdent){
        Question q = new Question();
        q.setQuestion(question);
        q.setQuestionIdent(questionIdent);
        return  q;
    }

    private AnswerOptions getAnswerOptions(String answer, String answerIdent, String questionIdent){
        AnswerOptions a = new AnswerOptions();
        a.setAnswer(answer);
        a.setAnswerIdent(answerIdent);
        a.setQuestionIdent(questionIdent);
        return a;
    }
}
