package ru.otus.spring.model;

import lombok.Data;

@Data
public class CorrectAnswer {

    private String correctAnswerIdent;
  //  private String answerIdent;
   // private String questionIdent;
    private Question question;
    private AnswerOptions answerOptions;

}
