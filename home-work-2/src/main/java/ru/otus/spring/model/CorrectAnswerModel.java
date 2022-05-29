package ru.otus.spring.model;

import lombok.Data;

@Data
public final class CorrectAnswerModel {

    private String correctAnswerIdent;
    private String answerIdent;
    private String questionIdent;

}
