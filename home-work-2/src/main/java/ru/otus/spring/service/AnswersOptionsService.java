package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptionsModel;

import java.util.List;

public interface AnswersOptionsService {
    List<AnswerOptionsModel> getAnswersOptions(List<String> reedData);
}
