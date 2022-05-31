package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.TestResultModel;
import ru.otus.spring.service.StudentTestResultService;

@Service
public class StudentTestResultServiceImpl implements StudentTestResultService {
    @Override
    public TestResultModel getTestResult(AnswersToTestQuestionsModel answersToTestQuestionsModel) {
        // Тут написать метод оценивания ответов студента
        return null;
    }
}
