package com.example.bookauthor.config;

import com.example.bookauthor.model.Author;
import com.example.bookauthor.model.Book;
import com.example.bookauthor.repository.AuthorRepository;
import com.example.bookauthor.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) {
        // Create authors
        Author author1 = new Author();
        author1.setName("J.K. Rowling");
        author1.setEmail("jkrowling@example.com");
        author1.setBiography("British author best known for the Harry Potter series.");
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("George R.R. Martin");
        author2.setEmail("grmartin@example.com");
        author2.setBiography("American novelist and short story writer, best known for A Song of Ice and Fire series.");
        authorRepository.save(author2);

        // Create books for author1
        Book book1 = new Book();
        book1.setTitle("Harry Potter and the Philosopher's Stone");
        book1.setIsbn("978-0747532743");
        book1.setDescription("The first book in the Harry Potter series.");
        book1.setPrice(new BigDecimal("19.99"));
        book1.setPublicationDate(LocalDate.of(1997, 6, 26));
        book1.setAuthor(author1);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Harry Potter and the Chamber of Secrets");
        book2.setIsbn("978-0747538493");
        book2.setDescription("The second book in the Harry Potter series.");
        book2.setPrice(new BigDecimal("21.99"));
        book2.setPublicationDate(LocalDate.of(1998, 7, 2));
        book2.setAuthor(author1);
        bookRepository.save(book2);

        // Create books for author2
        Book book3 = new Book();
        book3.setTitle("A Game of Thrones");
        book3.setIsbn("978-0553103540");
        book3.setDescription("The first book in A Song of Ice and Fire series.");
        book3.setPrice(new BigDecimal("24.99"));
        book3.setPublicationDate(LocalDate.of(1996, 8, 1));
        book3.setAuthor(author2);
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setTitle("A Clash of Kings");
        book4.setIsbn("978-0553108033");
        book4.setDescription("The second book in A Song of Ice and Fire series.");
        book4.setPrice(new BigDecimal("25.99"));
        book4.setPublicationDate(LocalDate.of(1998, 11, 16));
        book4.setAuthor(author2);
        bookRepository.save(book4);
    }
} 