package com.vittfiles.Alura_Oracle_Challenge_java_Spring.service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> myClass);
}
