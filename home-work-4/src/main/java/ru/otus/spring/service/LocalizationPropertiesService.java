package ru.otus.spring.service;

import org.springframework.context.ApplicationContext;

public interface LocalizationPropertiesService {

    String getLocalizationFile();

    String getLocalizationMessage(String messageIdent);

    void setLocalizationProperties(ApplicationContext context);

}
