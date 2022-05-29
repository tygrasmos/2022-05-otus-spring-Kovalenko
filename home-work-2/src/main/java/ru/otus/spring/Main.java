package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.PresentationService;
import ru.otus.spring.service.PrintQuestionsAnswersService;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        PresentationService presentationService = context.getBean(PresentationService.class);
        presentationService.presentQuestionsAndAnswersOptions();
        context.close();
    }
}
