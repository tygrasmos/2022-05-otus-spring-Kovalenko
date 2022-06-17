package ru.otus.spring.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


public class AnswersToTestQuestionsModel {

    private String fullName;
    private List<AnswerOnQuestionModel> questionResultModelList;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<AnswerOnQuestionModel> getQuestionResultModelList() {
        return questionResultModelList;
    }

    public void setQuestionResultModelList(List<AnswerOnQuestionModel> questionResultModelList) {
        this.questionResultModelList = new ArrayList<>(questionResultModelList);
    }
}
