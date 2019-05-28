package com.sigilius.spring5webapp.bootstrap;


import com.sigilius.spring5webapp.model.Author;
import com.sigilius.spring5webapp.model.Book;
import com.sigilius.spring5webapp.model.Publisher;
import com.sigilius.spring5webapp.repositories.AuthorRepository;
import com.sigilius.spring5webapp.repositories.BookRepository;
import com.sigilius.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //Publisher
        Publisher publisher1 = new Publisher();
        publisher1.setName("Wrox");
        publisher1.setStreetNumber("34");
        publisher1.setStreetName("Central Ave");
        publisher1.setCity("Indianapolis");
        publisher1.setState("IN");
        publisher1.setZipCode("46220");
        publisherRepository.save(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Harper Collins");
        publisher2.setStreetNumber("123");
        publisher2.setStreetName("Elm St.");
        publisher2.setCity("New York");
        publisher2.setState("NY");
        publisher2.setZipCode("23465");
        publisherRepository.save(publisher2);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher2);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher1);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
