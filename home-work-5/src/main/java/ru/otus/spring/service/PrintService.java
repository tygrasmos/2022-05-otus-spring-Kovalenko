package ru.otus.spring.service;

import java.util.List;

public interface PrintService {

    void print(List<?> objectList, String objectName);

    void print(String str);

}
