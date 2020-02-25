package com.brain.book.library.service;

import com.brain.book.library.dao.AuthorRepository;
import com.brain.book.library.dao.BookRepository;
import com.brain.book.library.model.Author;
import com.brain.book.library.model.Book;
import com.brain.book.library.model.GanreEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class BookLibraryService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public void addNewBook(Book book){
        Author author = book.getAuthor();
        if (author == null){
            throw new RuntimeException("Невозможно сохранить книгу без автора");
        }
        if (author.getId() == null){
            author = authorRepository.save(author);
            book.setAuthor(author);
        }
        bookRepository.save(book);
    }


    public Author findAuthorByFullName (String name, String secondName, String lastName){
        Author author = authorRepository
                .getAuthorByNameAndSecondNameAndLastName(name, secondName, lastName)
                .orElse(null);
        if (author == null){
            throw new RuntimeException("Невозможно найти автора по данным ФИО");
        }
        return author;
    }


    public List<Book> findBooksByGanres(Set<GanreEnum> ganres){
        log.info("Выводим список книг по жанрам");
        return bookRepository.findBooksByGanreIn(ganres);
    }

    public List<Book> findBooksByAuthor(String name, String secondName, String lastName){
        log.info("Выводим список книг по автору");
        Author author = findAuthorByFullName(name, secondName, lastName);
        return bookRepository.findBooksByAuthor(author);
    }
}
