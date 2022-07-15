package ru.otus.spring.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocalizationPropertiesService;

import java.util.Locale;

@Service
public class LocalizationPropertiesServiceImpl implements LocalizationPropertiesService {

    private final ApplicationContext context;
    private static final String PATH = "properties.localization.";

    public LocalizationPropertiesServiceImpl(ApplicationContext context){
        this.context = context;
    }

    @Override
    public String getLocalizationFile() {
        return context.getMessage(PATH.concat("file.path"), null, Locale.getDefault());
    }

    @Override
    public String getLocalizationMessage(String messageIdent) {
        return context.getMessage(PATH.concat("message.").concat(messageIdent), null, Locale.getDefault());
    }
}
