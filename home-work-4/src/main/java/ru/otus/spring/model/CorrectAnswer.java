package ru.otus.spring.model;



public class CorrectAnswer {

    private String correctAnswerIdent;
    private Question question;
    private AnswerOptions answerOptions;

    public String getCorrectAnswerIdent() {
        return correctAnswerIdent;
    }

    public void setCorrectAnswerIdent(String correctAnswerIdent) {
        this.correctAnswerIdent = correctAnswerIdent;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        Question newQuestion = new Question();
        newQuestion.setQuestion(question.getQuestion());
        newQuestion.setQuestionIdent(question.getQuestionIdent());
        this.question = newQuestion;
    }

    public AnswerOptions getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(AnswerOptions answerOptions) {
        AnswerOptions newAnswerOptions = new AnswerOptions();
        newAnswerOptions.setAnswer(answerOptions.getAnswer());
        newAnswerOptions.setAnswerIdent(answerOptions.getAnswerIdent());
        newAnswerOptions.setQuestionIdent(answerOptions.getQuestionIdent());
        this.answerOptions = newAnswerOptions;
    }
}
