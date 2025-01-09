package com.vittfiles.Alura_Oracle_Challenge_java_Spring;

import com.vittfiles.Alura_Oracle_Challenge_java_Spring.model.Author;
import com.vittfiles.Alura_Oracle_Challenge_java_Spring.model.Book;
import com.vittfiles.Alura_Oracle_Challenge_java_Spring.model.BookModel;
import com.vittfiles.Alura_Oracle_Challenge_java_Spring.repository.AuthorRepository;
import com.vittfiles.Alura_Oracle_Challenge_java_Spring.repository.BookRepository;
import com.vittfiles.Alura_Oracle_Challenge_java_Spring.service.BooksApiService;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner input = new Scanner(System.in);
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BooksApiService booksApiService = new BooksApiService();

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void run(){
        int option = -1;
        while(option != 0){
            String menuText = """
                    ------------
                    Elija la opción a través de su número:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menuText);
            try{
                option = input.nextInt();
                input.nextLine();

                switch(option){
                    case 1:
                        searchBookByTitle();
                        break;
                    case 2:
                        showBooksStored();
                        break;
                    case 3:
                        showAuthorsStored();
                        break;
                    case 4:
                        showAuthorAliveAtYear();
                        break;
                    case 5:
                        showBooksByLanguage();
                        break;
                    case 0:
                        System.out.println("cerrando la aplicación");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e){
                System.out.println("Ingresar una opción");
                option = -1;
                input.nextLine();
            }
        }
        input.close();
    }

    private void searchBookByTitle() {
        System.out.println("Ingresar el numbre del libro que desea buscar");
        String title = input.nextLine();
        try{
            BookModel bookModel = booksApiService.searchBookByTitle(title);
            Book book = new Book(bookModel);
            Optional<Book> localBook = bookRepository.checkIfBookExistInDB(book.getTitle());
            if(localBook.isEmpty()){
                System.out.println(book);
                List<String> names =  book.getAuthors().stream()
                        .map(Author::getName)
                        .collect(Collectors.toList());
                List<Author> localAuthors = authorRepository.checkIfAuthorExistInDB(names);

                localAuthors.forEach(a -> a.addBook(book));

                book.addAuthors(localAuthors);
                this.authorRepository.saveAll(book.getAuthors());
                bookRepository.save(book);
            }else{
                System.out.println("El libro ya se encuentra catalogado en la base de datos\n");
            }
        } catch (NullPointerException e) {
            System.out.println("Líbro no encontrado\n");
        }
    }

    private void showBooksStored() {
        List<Book> books = bookRepository.findAll();
        showBooks(books);
    }

    private void showAuthorsStored() {
        List<Author> authors = authorRepository.findAll();
        showAuthors(authors);
    }

    private void showAuthorAliveAtYear() {
        System.out.println("Ingresa el año: Ej. 1999");
        try{
            int year = Integer.parseInt(input.nextLine());
            if(year > 10000){
                throw new NumberFormatException("Año demaciado grande");
            }
            List<Author> authors = authorRepository.getAuthorBeforDeathYear(year);
            showAuthors(authors);
        } catch (NumberFormatException e){
            System.out.println("Año ingresado incorrectamente\n");
        }
    }

    private void showBooksByLanguage() {
        System.out.println("Ingresa un lenguaje en formato de código de dos caracteres:");
        System.out.println("(Ej. es = español, en = inglés, pt = portugués, fr = francés)");
        String lang = input.nextLine();
        if(lang.length() == 2){
            List<Book> books = bookRepository.getBooksByLanguage(lang);
            showBooks(books);
        }else{
            System.out.println("Número de caracteres inválido\n");
        }
    }

    private void showBooks(List<Book> books){
        System.out.println();
        if(books.isEmpty()){
            System.out.println("No se encontraron libros\n");
        }else{
            books.stream()
                    .sorted(Comparator.comparing(Book::getDownloadCount).reversed())
                    .forEach(System.out::println);
        }
    }

    private void showAuthors(List<Author> authors){
        System.out.println();
        if(authors.isEmpty()){
            System.out.println("No se encontraron autores\n");
        }else{
            authors.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .forEach(System.out::println);
        }
    }
}
