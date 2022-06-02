package ru.otus.spring.service;

import ru.otus.spring.model.QuestionModel;

import java.util.List;

public interface QuestionService {

    List<QuestionModel> getQuestions(List<String> reedData);

}
