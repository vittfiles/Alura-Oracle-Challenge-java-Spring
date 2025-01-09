package com.vittfiles.Alura_Oracle_Challenge_java_Spring.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    private int downloadCount;
    private String language;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> author = new HashSet<>();

    public Book(){}
    public Book(BookModel bookModel){
        this.title = bookModel.title();
        this.downloadCount = bookModel.downloadCount();
        if(!bookModel.languages().isEmpty()) {
            this.language = bookModel.languages().getFirst();
        }else{
            this.language = "";
        }
        bookModel.authors().forEach(a -> author.add(new Author(a,this)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Author> getAuthors() {
        return author;
    }

    public void addAuthors(List<Author> author) {
        Set<Author> toAdd = new HashSet<>();
        for (Author a: this.author){
            boolean founded = false;
            for (Author newA: author){
                if(a.getName().equals(newA.getName())){
                    toAdd.add(newA);
                    founded = true;
                }
            }
            if(!founded){
                toAdd.add(a);
            }
        }
        this.author = toAdd;
    }

    @Override
    public String toString() {
        return "------- LIBRO -------\n"+
                " Título: " + title +
                ",\n Autor/a: " + author.stream().map(a-> a.getName()).collect(Collectors.joining(" ; ")) +
                ",\n Idioma: " + language +
                ",\n Número de descargas: " + downloadCount
                +"\n----------------------\n";
    }
}
