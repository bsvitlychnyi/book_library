package com.brain.book.library.dao;

import com.brain.book.library.model.Author;
import com.brain.book.library.model.Book;
import com.brain.book.library.model.GanreEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBooksByAuthor(Author author);

    List<Book> findBooksByGanreIn(Collection<GanreEnum> ganres);

}
