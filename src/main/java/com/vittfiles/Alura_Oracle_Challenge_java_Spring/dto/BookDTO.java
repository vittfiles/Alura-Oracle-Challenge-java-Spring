package com.vittfiles.Alura_Oracle_Challenge_java_Spring.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record BookDTO(
        String title,
        String languages,
        int downloadCount,
        List<AuthorDTO> authors
) {
}
