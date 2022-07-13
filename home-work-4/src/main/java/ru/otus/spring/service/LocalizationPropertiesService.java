package ru.otus.spring.service;


public interface LocalizationPropertiesService {

    String getLocalizationFile();

    String getLocalizationMessage(String messageIdent);

}
