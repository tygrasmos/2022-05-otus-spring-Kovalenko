package ru.otus.spring.service;

import ru.otus.spring.model.CorrectAnswerModel;

import java.util.List;

public interface CorrectAnswerService {
    List<CorrectAnswerModel> getCorrectAnswers(List<String> reedData);
}
