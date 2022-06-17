package ru.otus.spring.model;

import lombok.Data;

@Data
public class AnswerOnQuestion {

  //  private String questionIdent;
  //  private String answerIdent;

    private Question question;
    private AnswerOptions answerOptions;

}
