package ru.otus.spring.model;


import java.util.ArrayList;

public class TestResult {

    private AnswersToTestQuestionsModel answersToTestQuestionsModel;
    private Integer questionsQuantity;
    private Integer correctAnswersQuantity;
    private Integer testScore;
    private Boolean isOffset;

    public AnswersToTestQuestionsModel getAnswersToTestQuestionsModel() {
        return answersToTestQuestionsModel;
    }

    public void setAnswersToTestQuestionsModel(AnswersToTestQuestionsModel answersToTestQuestionsModel) {
        AnswersToTestQuestionsModel newAnswersToTestQuestionsModel = new AnswersToTestQuestionsModel();
        newAnswersToTestQuestionsModel.setFullName(answersToTestQuestionsModel.getFullName());
        newAnswersToTestQuestionsModel.setQuestionResultModelList(
                new ArrayList<>(answersToTestQuestionsModel.getQuestionResultModelList()));
        this.answersToTestQuestionsModel = newAnswersToTestQuestionsModel;
    }

    public Integer getQuestionsQuantity() {
        return questionsQuantity;
    }

    public void setQuestionsQuantity(Integer questionsQuantity) {
        this.questionsQuantity = questionsQuantity;
    }

    public Integer getCorrectAnswersQuantity() {
        return correctAnswersQuantity;
    }

    public void setCorrectAnswersQuantity(Integer correctAnswersQuantity) {
        this.correctAnswersQuantity = correctAnswersQuantity;
    }

    public Integer getTestScore() {
        return testScore;
    }

    public void setTestScore(Integer testScore) {
        this.testScore = testScore;
    }

    public Boolean getOffset() {
        return isOffset;
    }

    public void setOffset(Boolean offset) {
        isOffset = offset;
    }
}
