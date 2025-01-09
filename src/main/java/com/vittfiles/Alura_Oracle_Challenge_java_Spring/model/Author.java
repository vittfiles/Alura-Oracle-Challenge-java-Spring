package com.vittfiles.Alura_Oracle_Challenge_java_Spring.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int birthYear;
    private int deathYear;
    @ManyToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Book> book = new HashSet<>();

    public Author(){}

    public Author(AuthorModel authorModel,Book book){
        this.name = authorModel.name();
        this.birthYear = authorModel.birthYear();
        this.deathYear = authorModel.deathYear();
        this.book.add(book);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public Set<Book> getBooks() {
        return book;
    }

    public void addBook(Book book) {
        this.book.add(book);
    }

    @Override
    public String toString() {
        return "Autor: " + name +
                "\n Fecha de nacimiento: " + birthYear +
                "\n Fecha de fallecimiento: " + deathYear +
                "\n Libros: [" + book.stream().map(b -> b.getTitle()).collect(Collectors.joining("/ "))
                + "]\n";
    }
}
