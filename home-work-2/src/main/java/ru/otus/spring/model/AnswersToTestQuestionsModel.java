package ru.otus.spring.model;


import lombok.Data;

import java.util.List;

@Data
public class AnswersToTestQuestionsModel {

    private String fullName;
    private List<AnswerOnQuestionModel> questionResultModelList;

}
