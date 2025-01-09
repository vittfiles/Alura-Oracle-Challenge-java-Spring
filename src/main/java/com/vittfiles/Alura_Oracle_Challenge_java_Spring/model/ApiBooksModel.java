package com.vittfiles.Alura_Oracle_Challenge_java_Spring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiBooksModel(
        int count,
        String next,
        String previous,
        @JsonAlias("results") List<BookModel> books,
        @JsonAlias("detail") String errors
) {
}
