package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.AnswersToTestQuestionsModel;
import ru.otus.spring.model.QuestionModel;
import ru.otus.spring.service.QuestionAnsweringService;

import java.util.List;

@Service
public class QuestionAnsweringServiceImpl implements QuestionAnsweringService {

    @Override
    public AnswersToTestQuestionsModel getAnswersOnTest(List<QuestionModel> questionModelList) {
        // Написать метод распечатки и считывания ответов студента
        return null;
    }
}
