package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptions;

import java.util.List;

public interface AnswersOptionsService {

    List<AnswerOptions> getAnswersOptions(List<String> reedData);

}
