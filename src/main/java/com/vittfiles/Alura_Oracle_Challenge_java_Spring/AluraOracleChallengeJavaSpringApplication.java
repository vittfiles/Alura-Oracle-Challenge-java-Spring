package com.vittfiles.Alura_Oracle_Challenge_java_Spring;

import com.vittfiles.Alura_Oracle_Challenge_java_Spring.repository.AuthorRepository;
import com.vittfiles.Alura_Oracle_Challenge_java_Spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraOracleChallengeJavaSpringApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(AluraOracleChallengeJavaSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(bookRepository,authorRepository);
        main.run();
    }
}
