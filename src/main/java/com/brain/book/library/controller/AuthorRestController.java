package com.brain.book.library.controller;

import com.brain.book.library.dao.AuthorRepository;
import com.brain.book.library.model.Author;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authors")
@AllArgsConstructor
public class AuthorRestController {

    private AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity <Iterable<Author>> getAuthorList(){
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity <Author> getAuthor(@PathVariable Long id){
        return new ResponseEntity<>(authorRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeAuthor(@PathVariable Long id){
        authorRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Author> createAuthor(@RequestBody Author author){
        Author newAuthor = authorRepository.save(author);
        return new ResponseEntity<>(newAuthor, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author){
        Author updatedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }
}
