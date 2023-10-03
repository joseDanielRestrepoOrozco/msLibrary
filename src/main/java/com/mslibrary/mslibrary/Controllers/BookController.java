package com.mslibrary.mslibrary.Controllers;

import com.mslibrary.mslibrary.Models.Author;
import com.mslibrary.mslibrary.Models.Book;
import com.mslibrary.mslibrary.Repositories.AuthorRepository;
import com.mslibrary.mslibrary.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository theBookRepository;

    @Autowired
    private AuthorRepository theAuthorController;

    @GetMapping("")
    public List<Book> index() {
        return this.theBookRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book store(@RequestBody Book newBook) {
        return this.theBookRepository.save(newBook);
    }

    @GetMapping("{id}")
    public Book show(@PathVariable String id) {
        return this.theBookRepository.findById(id).orElse(null);
    }

    @PutMapping("{id}")
    public Book update(@PathVariable String id, @RequestBody Book theNewBook) {
        Book theActualBook = this.theBookRepository.findById(id).orElse(null);
        if (theActualBook != null) {
            theActualBook.setName(theNewBook.getName());
            theActualBook.setDate(theNewBook.getDate());
            theActualBook.setGenre(theNewBook.getGenre());
            return this.theBookRepository.save(theActualBook);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Book theBook = this.theBookRepository.findById(id).orElse(null);
        if (theBook != null) {
            this.theBookRepository.delete(theBook);
        }
    }

    @PutMapping("{book_id}/author/{author_id}")
    public Book matchBookAuthor(@PathVariable String book_id, @PathVariable String author_id) {
        Book theActualBook = this.theBookRepository.findById(book_id).orElse(null);
        Author theActualAuthor = this.theAuthorController.findById(author_id).orElse(null);
        if (theActualBook != null && theActualAuthor != null) {
            theActualBook.setAuthor(theActualAuthor);
            return this.theBookRepository.save(theActualBook);
        } else {
            return null;
        }
    }

    @DeleteMapping("{book_id}/role")
    public Book unMatchBookAuthor(@PathVariable String book_id) {
        Book theActualBook = this.theBookRepository.findById(book_id).orElse(null);
        if (theActualBook != null) {
            theActualBook.setAuthor(null);
            return this.theBookRepository.save(theActualBook);
        } else {
            return null;
        }
    }
}
