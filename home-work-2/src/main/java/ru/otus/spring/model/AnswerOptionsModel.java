package ru.otus.spring.model;

import lombok.Data;

@Data
public final class AnswerOptionsModel {

    private String answer;
    private String answerIdent;
    private String questionIdent;

}