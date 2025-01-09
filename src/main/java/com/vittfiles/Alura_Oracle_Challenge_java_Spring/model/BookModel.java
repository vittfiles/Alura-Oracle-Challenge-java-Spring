package com.vittfiles.Alura_Oracle_Challenge_java_Spring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookModel(
        String title,
        List<AuthorModel> authors,
        List<String> languages,
        @JsonAlias("download_count") int downloadCount,
        @JsonAlias("detail") String errors
) {
}
