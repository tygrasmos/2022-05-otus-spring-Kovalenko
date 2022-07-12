package ru.otus.spring.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocalizationPropertiesService;

import java.util.Locale;

@Service
public class LocalizationPropertiesServiceImpl implements LocalizationPropertiesService {

    private ApplicationContext context;
    private static final String path = "properties.localization.";

    @Override
    public String getLocalizationFile() {
        return context.getMessage(path.concat("file.path"), null, Locale.getDefault());
    }

    @Override
    public String getLocalizationMessage(String messageIdent) {
        return context.getMessage(path.concat("message.").concat(messageIdent), null, Locale.getDefault());
    }

    @Override
    public void setLocalizationProperties(ApplicationContext context) {
        this.context = context;
    }
}
