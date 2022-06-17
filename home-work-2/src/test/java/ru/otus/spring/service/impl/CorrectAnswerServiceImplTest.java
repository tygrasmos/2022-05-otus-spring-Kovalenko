package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.model.CorrectAnswerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorrectAnswerServiceImplTest {

    private final String [] testStringArray = {"C1Q1 A1", "Q1 Question?", "A2Q1 Answer2", "C2Q2 A3"};

    @DisplayName("Correct execution of the method Get a list of correct answer.")
    @Test
    void mustBeReceivedAValidCorrectAnswerList(){
        CorrectAnswerServiceImpl correctAnswerService = new CorrectAnswerServiceImpl();
        Assertions.assertEquals(getTestAnswerOptionsModelList()
                , correctAnswerService.getCorrectAnswers(Arrays.asList(testStringArray)));
    }

    private List<CorrectAnswerModel> getTestAnswerOptionsModelList(){
        List<CorrectAnswerModel> list = new ArrayList<>();
        CorrectAnswerModel correctAnswer1 = new CorrectAnswerModel();
        correctAnswer1.setAnswerIdent("A1");
        correctAnswer1.setQuestionIdent("Q1");
        correctAnswer1.setCorrectAnswerIdent("C1");
        CorrectAnswerModel correctAnswer2 = new CorrectAnswerModel();
        correctAnswer2.setAnswerIdent("A3");
        correctAnswer2.setQuestionIdent("Q2");
        correctAnswer2.setCorrectAnswerIdent("C2");
        list.add(correctAnswer1);
        list.add(correctAnswer2);
        return list;
    }
}
