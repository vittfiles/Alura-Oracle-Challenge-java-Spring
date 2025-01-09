package com.vittfiles.Alura_Oracle_Challenge_java_Spring.service;

import com.vittfiles.Alura_Oracle_Challenge_java_Spring.model.ApiBooksModel;
import com.vittfiles.Alura_Oracle_Challenge_java_Spring.model.BookModel;

import java.net.URLEncoder;
import java.util.Optional;

public class BooksApiService {
    private String url = "https://gutendex.com/books/";
    private ApiConnection apiConnection = new ApiConnection();
    private DataConverter dataConverter = new DataConverter();

    public BookModel searchBookByTitle(String title){
        String json = apiConnection.getData(url + "?search=" + URLEncoder.encode(title));
        ApiBooksModel response = dataConverter.getData(json, ApiBooksModel.class);

        Optional<BookModel> book = response.books().stream()
                .filter(b -> b.title().toLowerCase().contains(title.toLowerCase()))
                .findFirst();
        if(book.isPresent()){
            return book.get();
        }else{
            return null;
        }
    }
}
