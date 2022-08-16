package com.wrwp.spring5webapp.bootstrap;

import com.wrwp.spring5webapp.repositories.AuthorRepository;
import com.wrwp.spring5webapp.repositories.BookRepository;
import com.wrwp.spring5webapp.repositories.PublisherRepository;
import com.wrwp.spring5webapp.domain.Author;
import com.wrwp.spring5webapp.domain.Book;
import com.wrwp.spring5webapp.domain.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        // Important for publisher to be declared first in order to avoid error in DB transaction for
        // one-to-many relationship
        Publisher oreilly = new Publisher();
        oreilly.setName("OReilly");
        oreilly.setAddress("111 Hacker Way Menlo Park CA");
        publisherRepository.save(oreilly);


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(oreilly);
        oreilly.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "123123123123");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(oreilly);
        oreilly.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);


        System.out.println("Started in Bootstrap");
        System.out.println(String.format("Number of books: %o \nNumber of authors: %o \nNumber of publishers: %o", bookRepository.count(), authorRepository.count(), publisherRepository.count()));
    }
}
