package ru.otus.spring.model;

import lombok.Data;

@Data
public class AnswerOnQuestion {

    private Question question;
    private AnswerOptions answerOptions;

}
