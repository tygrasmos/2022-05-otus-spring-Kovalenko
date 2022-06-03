package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOptionsModel;
import ru.otus.spring.model.CorrectAnswerModel;
import ru.otus.spring.model.QuestionModel;
import ru.otus.spring.model.TestResultModel;
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
    public void printQuestionsAndAnswerOptionsAndCorrectAnswers(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList
            , List<CorrectAnswerModel> correctAnswerModelList) {
        printQuestionsAndAnswerOptions(answerOptionsModelList, questionModelList);
        printQuestionsAndCorrectAnswers(answerOptionsModelList, questionModelList, correctAnswerModelList);
    }

    @Override
    public void printQuestions(List<QuestionModel> questionModelList) {
        questionModelList.forEach(q -> {
            print(QUESTIONS_TITLE);
            print(q);
            print(END_TITLE);
        });
    }

    @Override
    public void printQuestionsAndAnswerOptions(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList) {
        print(QUESTIONS_AND_OPTIONS_ANSWERS_TITLE);
        questionModelList.forEach(q -> {
            print(q);
            answerOptionsModelList.forEach(a -> {
                if (a.getQuestionIdent().equals(q.getQuestionIdent())) {
                    print(a);
                }
            });
        });
        print(END_TITLE);
    }

    @Override
    public void printTestResult(TestResultModel testResultModel) {
        print(RESULT_TITLE);
        print(testResultModel);
        print(END_TITLE);
    }

    @Override
    public void printQuestionsAndCorrectAnswers(List<AnswerOptionsModel> answerOptionsModelList
            , List<QuestionModel> questionModelList
            , List<CorrectAnswerModel> correctAnswerModelList) {
        print(CORRECT_ANSWERS_TO_QUESTIONS_TITLE);
        correctAnswerModelList.forEach(ca ->{
            questionModelList.forEach(q ->{
                if (q.getQuestionIdent().equals(ca.getQuestionIdent())){
                    print(q);
                }
            });
            answerOptionsModelList.forEach(ao ->{
                if (ao.getAnswerIdent().equals(ca.getAnswerIdent())
                        && ao.getQuestionIdent().equals(ca.getQuestionIdent())){
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
    public void printSingleQuestionAndAnswers(QuestionModel question, List<AnswerOptionsModel> answerOptionsList) {
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
        if (o.getClass().equals(QuestionModel.class)){
            QuestionModel q = (QuestionModel) o;
            System.out.println(q.getQuestionIdent() + " - " + q.getQuestion());
        } else if (o.getClass().equals(AnswerOptionsModel.class)){
            AnswerOptionsModel a = (AnswerOptionsModel) o;
            System.out.println("             " + a.getAnswerIdent() + " - " + a.getAnswer());
        } else if (o.getClass().equals(TestResultModel.class)) {
            TestResultModel tr = (TestResultModel) o;
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
