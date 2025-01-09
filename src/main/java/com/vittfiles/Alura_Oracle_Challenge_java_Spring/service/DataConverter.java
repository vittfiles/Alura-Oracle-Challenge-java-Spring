package com.vittfiles.Alura_Oracle_Challenge_java_Spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> myClass) {
        try {
            return objectMapper.readValue(json, myClass);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
