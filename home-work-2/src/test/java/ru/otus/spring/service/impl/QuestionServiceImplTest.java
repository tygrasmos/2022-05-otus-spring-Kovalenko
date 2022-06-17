package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.model.QuestionModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Class QuestionServiceImpl")
class QuestionServiceImplTest {

    private final String [] testStringArray = {"Q1 Question1?", "Q2 Question2?", "A1Q1 Answer1"};

    @DisplayName("Correct execution of the method Get a list of questions.")
    @Test
    void mustBeReceivedAValidQuestionList(){
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        Assertions.assertEquals(getTestQuestionModelList()
                , questionService.getQuestions(Arrays.asList(testStringArray)));
    }

    private List<QuestionModel> getTestQuestionModelList(){
        List<QuestionModel> list = new ArrayList<>();
        QuestionModel quest1 = new QuestionModel();
        quest1.setQuestion("Question1?");
        quest1.setQuestionIdent("Q1");
        QuestionModel quest2 = new QuestionModel();
        quest2.setQuestion("Question2?");
        quest2.setQuestionIdent("Q2");
        list.add(quest1);
        list.add(quest2);
        return list;
    }
}
