package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.PresentationService;

@ShellComponent
public class ApplicationCommands {

    private final PresentationService presentationService;

    public ApplicationCommands(PresentationService presentationService){
        this.presentationService = presentationService;
    }

    @ShellMethod(value = "Start Test", key = {"st", "startT"})
    public void startTest() {
        presentationService.presentQuestionsAndAnswersOptions();
    }

    @ShellMethod(value = "Show All Questions", key = {"sq", "showQ"})
    public void showAllQuestions() {
        presentationService.showAllQuestions();
    }

    @ShellMethod(value = "Show All Questions and Answers", key = {"sqa", "showQA"})
    public void showQuestionsAndAnswerOptionsAndCorrectAnswers() {
        presentationService.showQuestionsAndAnswerOptionsAndCorrectAnswers();
    }
}
