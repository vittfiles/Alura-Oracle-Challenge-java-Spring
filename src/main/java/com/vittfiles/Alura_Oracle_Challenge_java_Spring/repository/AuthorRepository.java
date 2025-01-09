package com.vittfiles.Alura_Oracle_Challenge_java_Spring.repository;

import com.vittfiles.Alura_Oracle_Challenge_java_Spring.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query("SELECT a FROM Author a WHERE a.name IN(:names)")
    List<Author> checkIfAuthorExistInDB(List<String> names);


    @Query("SELECT a FROM Author a WHERE a.deathYear > :year")
    List<Author> getAuthorBeforDeathYear(int year);
}
