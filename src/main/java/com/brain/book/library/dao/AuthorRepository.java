package com.brain.book.library.dao;

import com.brain.book.library.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> getAuthorByNameAndSecondNameAndLastName(String name, String secondName, String lastName);

    @Query("from Author a where a.name = :name and a.secondName = :secondName and a.lastName = :lastName")
    Optional<Author> getAuthorByFullName(@Param("name") String name,
                                         @Param("secondName") String secondName,
                                         @Param("lastName") String lastName);
}
