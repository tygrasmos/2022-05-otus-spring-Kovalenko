package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswerOnQuestion;
import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.PrintService;
import ru.otus.spring.service.QuestionAnsweringService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionAnsweringServiceImpl implements QuestionAnsweringService {

    private final PrintService printService;

    public QuestionAnsweringServiceImpl(PrintService printService){
        this.printService = printService;
    }

    private final static String END_TESTING_TITLE =
            "----------------------------   End Testing   ------------------------------";
    private final static String TEST_TITLE =
            "-------------------------   Begin Student Test  ---------------------------";
    private final static String NOTE =
            "The answer options should be entered together with the answer identifier, for example \"A2\".";
    private final Scanner sc = new Scanner(System.in);

    @Override
    public AnswersToTestQuestionsModel getAnswersOnTest(List<Question> questionList
            , List<AnswerOptions> answerOptionsList) {
        printService.print(TEST_TITLE);
        printService.print("");
        printService.print(NOTE);
        printService.print("");
        AnswersToTestQuestionsModel answersToTest = new AnswersToTestQuestionsModel();
        List<AnswerOnQuestion> questionResultModelList = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++){
            printService.printSingleQuestionAndAnswers(questionList.get(i), answerOptionsList);
            String str = sc.nextLine();
            AnswerOnQuestion answerOnQuestion = new AnswerOnQuestion();
            if (i == 0){
                answersToTest.setFullName(str);
            } else {
                answerOnQuestion.setQuestion(questionList.get(i));
                answerOnQuestion.setAnswerOptions(findAnswerOptions(answerOptionsList, str));
                questionResultModelList.add(answerOnQuestion);
            }
        }
        answersToTest.setQuestionResultModelList(questionResultModelList);
        printService.print(END_TESTING_TITLE);
        return answersToTest;
    }

    private AnswerOptions findAnswerOptions(List<AnswerOptions> answerOptionsList, String answerOptionsIdent){
        return answerOptionsList.stream()
                .filter(a -> a.getAnswerIdent().equals(answerOptionsIdent))
                .findFirst()
                .get();
    }
}
