package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.LocalizationPropertiesService;
import ru.otus.spring.service.PresentationService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
        LocalizationPropertiesService localizationPropertiesService = context.getBean(LocalizationPropertiesService.class);
        localizationPropertiesService.setLocalizationProperties(context);
        PresentationService presentationService = context.getBean(PresentationService.class);
        presentationService.presentQuestionsAndAnswersOptions();
    }
}
