package ru.otus.spring.service.impl;


import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocalizationPropertiesService;
import ru.otus.spring.service.ResourceService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final LocalizationPropertiesService localizationPropertiesService;

    public ResourceServiceImpl(LocalizationPropertiesService localizationPropertiesService){
        this.localizationPropertiesService = localizationPropertiesService;
    }

    @Override
    public List<String> reedFile() {

        List<String> reedData = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(
                localizationPropertiesService.getLocalizationFile());

        if (inputStream != null) {
            InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            try {
                String s;
                while ((s = reader.readLine()) != null) {
                    reedData.addAll(Arrays.asList(s.split(",")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return reedData;
        }

        return null;
    }
}
