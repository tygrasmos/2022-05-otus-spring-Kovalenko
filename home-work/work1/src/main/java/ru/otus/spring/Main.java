package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.PrintQuestionsAnswersService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PrintQuestionsAnswersService printQuestionsAnswersService = context.getBean(PrintQuestionsAnswersService.class);
        printQuestionsAnswersService.print();
        context.close();
    }
}
