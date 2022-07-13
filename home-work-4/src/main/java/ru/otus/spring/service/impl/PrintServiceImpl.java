package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.CorrectAnswer;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.TestResult;
import ru.otus.spring.service.LocalizationPropertiesService;
import ru.otus.spring.service.PrintService;

import java.util.List;

@Service
public class PrintServiceImpl implements PrintService {

    private final static String QUESTIONS_AND_OPTIONS_ANSWERS_TITLE = "QUESTIONS_AND_OPTIONS_ANSWERS_TITLE";
    private final static String CORRECT_ANSWERS_TO_QUESTIONS_TITLE = "CORRECT_ANSWERS_TO_QUESTIONS_TITLE";
    private final static String QUESTIONS_TITLE = "QUESTIONS_TITLE";
    private final static String END_TITLE = "END_TITLE";
    private final static String RESULT_TITLE = "RESULT_TITLE";
    private final static String FULL_NAME = "FULL_NAME";
    private final static String QUANTITY_QUESTIONS = "QUANTITY_QUESTIONS";
    private final static String QUANTITY_CORRECT_ANSWERS = "QUANTITY_CORRECT_ANSWERS";
    private final static String TEST_SCORE = "TEST_SCORE";
    private final static String OFFSET = "OFFSET";
    private final static String ERROR_MESSAGE = "ERROR_MESSAGE";

    private final LocalizationPropertiesService localizationPropertiesService;

    public PrintServiceImpl(LocalizationPropertiesService localizationPropertiesService){
        this.localizationPropertiesService = localizationPropertiesService;
    }


    @Override
    public void printQuestionsAndAnswerOptionsAndCorrectAnswers(List<AnswerOptions> answerOptionsList
            , List<Question> questionList
            , List<CorrectAnswer> correctAnswerList) {
        printQuestionsAndAnswerOptions(answerOptionsList, questionList);
        printQuestionsAndCorrectAnswers(answerOptionsList, questionList, correctAnswerList);
    }

    @Override
    public void printQuestions(List<Question> questionList) {
        questionList.forEach(q -> {
            print(QUESTIONS_TITLE);
            print(q);
            print(END_TITLE);
        });
    }

    @Override
    public void printQuestionsAndAnswerOptions(List<AnswerOptions> answerOptionsList
            , List<Question> questionList) {
        print(QUESTIONS_AND_OPTIONS_ANSWERS_TITLE);
        questionList.forEach(q -> {
            print(q);
            answerOptionsList.forEach(a -> {
                if (a.getQuestionIdent().equals(q.getQuestionIdent())) {
                    print(a);
                }
            });
        });
        print(END_TITLE);
    }

    @Override
    public void printTestResult(TestResult testResult) {
        print(RESULT_TITLE);
        print(testResult);
        print(END_TITLE);
    }

    @Override
    public void printQuestionsAndCorrectAnswers(List<AnswerOptions> answerOptionsList
            , List<Question> questionList
            , List<CorrectAnswer> correctAnswerList) {
        print(CORRECT_ANSWERS_TO_QUESTIONS_TITLE);
        correctAnswerList.forEach(ca ->{
            questionList.forEach(q ->{
                if (q.equals(ca.getQuestion())){
                    print(q);
                }
            });
            answerOptionsList.forEach(ao ->{
                if (ao.equals(ca.getAnswerOptions())
                        && ao.getQuestionIdent().equals(ca.getQuestion().getQuestionIdent())){
                    print(ao);
                }
            });
        });
        print(END_TITLE);
    }


    @Override
    public void printError() {
        print(ERROR_MESSAGE);
    }

    @Override
    public void printSingleQuestionAndAnswers(Question question, List<AnswerOptions> answerOptionsList) {
        String ident = question.getQuestionIdent();
        if (ident.contains("1") && ident.length() == 2){
            print(question);
        } else {
            print(question);
            answerOptionsList.forEach(a ->{
                if (a.getQuestionIdent().equals(question.getQuestionIdent())){
                    print(a);
                }
            });
        }
    }

    @Override
    public void print(Object o){
        if (o.getClass().equals(Question.class)){
            Question q = (Question) o;
            System.out.println(q.getQuestionIdent() + " - " + q.getQuestion());
        } else if (o.getClass().equals(AnswerOptions.class)){
            AnswerOptions a = (AnswerOptions) o;
            System.out.println("             " + a.getAnswerIdent() + " - " + a.getAnswer());
        } else if (o.getClass().equals(TestResult.class)) {
            TestResult tr = (TestResult) o;
            System.out.println(getLocalizedMessage(FULL_NAME) + tr.getAnswersToTestQuestionsModel().getFullName());
            System.out.println(getLocalizedMessage(QUANTITY_QUESTIONS) + tr.getQuestionsQuantity());
            System.out.println(getLocalizedMessage(QUANTITY_CORRECT_ANSWERS) + tr.getCorrectAnswersQuantity());
            System.out.println(getLocalizedMessage(TEST_SCORE) + tr.getTestScore());
            System.out.println(getLocalizedMessage(OFFSET)
                    + (tr.getOffset().equals(Boolean.TRUE)
                    ? getLocalizedMessage("offset")
                    : getLocalizedMessage("fail")));
        } else {
            String str = (String) o;
            System.out.println(getLocalizedMessage(str));
        }
    }

    private String getLocalizedMessage(String messageIdent){
        return messageIdent.equals("") ? "" : localizationPropertiesService.getLocalizationMessage(messageIdent);
    }
}
