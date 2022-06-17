package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.CorrectAnswer;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.TestResult;
import ru.otus.spring.service.PrintService;

import java.util.List;

@Service
public class PrintServiceImpl implements PrintService {

    private final static String QUESTIONS_AND_OPTIONS_ANSWERS_TITLE =
            "-------------------- Questions and Options Answers ------------------------";
    private final static String CORRECT_ANSWERS_TO_QUESTIONS_TITLE =
            "-------------------- Correct answers to questions -------------------------";
    private final static String QUESTIONS_TITLE =
            "-----------------------------  Questions  ---------------------------------";
    private final static String END_TITLE =
            "---------------------------------   End -----------------------------------";
    private final static String RESULT_TITLE =
            "-------------------------   Student Test Result  ---------------------------";
    private final static String FULL_NAME                = "Full name of the person being tested : ";
    private final static String QUANTITY_QUESTIONS       = "Number of questions in the test      : ";
    private final static String QUANTITY_CORRECT_ANSWERS = "Number of correct answers            : ";
    private final static String TEST_SCORE               = "Test score                           : ";
    private final static String OFFSET                   = "Is Offset                            : ";
    private final static String ERROR_MESSAGE = "Failed to reed data from file.";


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
            System.out.println(FULL_NAME + tr.getAnswersToTestQuestionsModel().getFullName());
            System.out.println(QUANTITY_QUESTIONS + tr.getQuestionsQuantity());
            System.out.println(QUANTITY_CORRECT_ANSWERS + tr.getCorrectAnswersQuantity());
            System.out.println(TEST_SCORE + tr.getTestScore());
            System.out.println(OFFSET + (tr.getIsOffset().equals(Boolean.TRUE) ? "offset" : "fail"));
        } else {
            String str = (String) o;
            System.out.println(str);
        }
    }
}
