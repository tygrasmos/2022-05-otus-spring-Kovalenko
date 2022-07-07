package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.model.AnswerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Class AnswerOptionsServiceImpl")
class AnswerOptionsServiceImplTest {

    private final String [] testStringArray = {"Q1 Question1?", "A1Q1 Answer1", "A2Q1 Answer2"};

    @DisplayName("Correct execution of the method Get a list of answer options.")
    @Test
    void mustBeReceivedAValidAnswersOptionsList(){
        AnswerOptionsServiceImpl answerOptionsService = new AnswerOptionsServiceImpl();
        Assertions.assertEquals(getTestAnswerOptionsModelList()
                , answerOptionsService.getAnswersOptions(Arrays.asList(testStringArray)));
    }

    private List<AnswerOptions> getTestAnswerOptionsModelList(){
        List<AnswerOptions> list = new ArrayList<>();
        AnswerOptions answer1 = new AnswerOptions();
        answer1.setAnswer("Answer1");
        answer1.setAnswerIdent("A1");
        answer1.setQuestionIdent("Q1");
        AnswerOptions answer2 = new AnswerOptions();
        answer2.setAnswer("Answer2");
        answer2.setAnswerIdent("A2");
        answer2.setQuestionIdent("Q1");
        list.add(answer1);
        list.add(answer2);
        return list;
    }
}
