package com.vittfiles.Alura_Oracle_Challenge_java_Spring.repository;

import com.vittfiles.Alura_Oracle_Challenge_java_Spring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT b FROM Book b WHERE b.title = :text")
    Optional<Book> checkIfBookExistInDB(String text);

    @Query("SELECT b FROM Book b WHERE b.language = :text")
    List<Book> getBooksByLanguage(String text);
}
