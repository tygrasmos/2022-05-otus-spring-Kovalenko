package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Class QuestionServiceImpl")
@SpringBootTest
class QuestionServiceImplTest {

    private final String [] testStringArray = {"Q1 Question1?", "Q2 Question2?", "A1Q1 Answer1"};

    @DisplayName("Correct execution of the method Get a list of questions.")
    @Test
    void mustBeReceivedAValidQuestionList(){
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        Assertions.assertEquals(getTestQuestionModelList()
                , questionService.getQuestions(Arrays.asList(testStringArray)));
    }

    private List<Question> getTestQuestionModelList(){
        List<Question> list = new ArrayList<>();
        Question quest1 = new Question();
        quest1.setQuestion("Question1?");
        quest1.setQuestionIdent("Q1");
        Question quest2 = new Question();
        quest2.setQuestion("Question2?");
        quest2.setQuestionIdent("Q2");
        list.add(quest1);
        list.add(quest2);
        return list;
    }
}
