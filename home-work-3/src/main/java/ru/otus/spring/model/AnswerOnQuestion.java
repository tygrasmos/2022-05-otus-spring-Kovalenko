package ru.otus.spring.model;



public class AnswerOnQuestion {

    private Question question;
    private AnswerOptions answerOptions;


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
