package ru.otus.spring.model;

import lombok.Data;

@Data
public class TestResult {

    private AnswersToTestQuestionsModel answersToTestQuestionsModel;
    private Integer questionsQuantity;
    private Integer correctAnswersQuantity;
    private Integer testScore;
    private Boolean isOffset;

}
