package ru.otus.spring.model;

import lombok.Data;

@Data
public final class TestResultModel {

    private AnswersToTestQuestionsModel answersToTestQuestionsModel;
    private Integer questionsQuantity;
    private Integer correctAnswersQuantity;
    private Integer testScore;

}
