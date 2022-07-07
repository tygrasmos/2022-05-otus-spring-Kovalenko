package ru.otus.spring.model;

import java.util.ArrayList;
import java.util.List;


public class AnswersToTestQuestionsModel {

    private String fullName;
    private List<AnswerOnQuestion> questionResultModelList;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<AnswerOnQuestion> getQuestionResultModelList() {
        return questionResultModelList;
    }

    public void setQuestionResultModelList(List<AnswerOnQuestion> questionResultModelList) {
        this.questionResultModelList = new ArrayList<>(questionResultModelList);
    }
}
