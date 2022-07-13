package ru.otus.spring.service;

import ru.otus.spring.model.AnswerOptions;
import ru.otus.spring.model.CorrectAnswer;
import ru.otus.spring.model.Question;

import java.util.List;

public interface CorrectAnswerService {

    List<CorrectAnswer> getCorrectAnswers(List<String> reedData
            , List<Question> questionList
            , List<AnswerOptions> answerOptionsList);

}
