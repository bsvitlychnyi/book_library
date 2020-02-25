package com.brain.book.library.controller;

import com.brain.book.library.dao.BookRepository;
import com.brain.book.library.model.Book;
import com.brain.book.library.model.GanreEnum;
import com.brain.book.library.service.BookLibraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/books/")
@AllArgsConstructor
public class BookRestController {

    private BookRepository bookRepository;
    private BookLibraryService bookLibraryService;

    @GetMapping
    public ResponseEntity <Iterable<Book>> getAuthorList(){
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity <Book> getAuthor(@PathVariable Long id){
        return new ResponseEntity<>(bookRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeAuthor(@PathVariable Long id){
        bookRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Book> createAuthor(@RequestBody Book book){
        Book newBook = bookRepository.save(book);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Book> updateAuthor(@RequestBody Book book){
        Book updatedBook = bookRepository.save(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @GetMapping("search-by-author/")
    public ResponseEntity<List<Book>> findBooksByAuthor(@RequestParam("name") String name,
                                                @RequestParam("lastName") String lastName,
                                                @RequestParam("secondName") String secondName){
        return new ResponseEntity<>(bookLibraryService.findBooksByAuthor(name, secondName, lastName), HttpStatus.OK);
    }

    @GetMapping("search-by-genre/")
    public ResponseEntity<List<Book>> findBooksByGenre(@RequestParam("genre") Set<GanreEnum> genres){
        return new ResponseEntity<>(bookLibraryService.findBooksByGanres(genres), HttpStatus.OK);
    }
}
