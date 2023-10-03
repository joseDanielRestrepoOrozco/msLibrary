package com.mslibrary.mslibrary.Controllers;

import com.mslibrary.mslibrary.Models.Author;
import com.mslibrary.mslibrary.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository theAuthorRepository;

    @GetMapping("")
    public List<Author> index() {
        return this.theAuthorRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Author store(@RequestBody Author newAuthor) {
        return this.theAuthorRepository.save(newAuthor);
    }

    @GetMapping("{id}")
    public Author show(@PathVariable String id) {
        return this.theAuthorRepository.findById(id).orElse(null);
    }

    @PutMapping("{id}")
    public Author update(@PathVariable String id, @RequestBody Author theNewAuthor) {
        Author theActualAuthor = this.theAuthorRepository.findById(id).orElse(null);
        if (theActualAuthor != null) {
            theActualAuthor.setName(theNewAuthor.getName());
            theActualAuthor.setNationality(theNewAuthor.getNationality());
            return this.theAuthorRepository.save(theActualAuthor);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Author theAuthor = this.theAuthorRepository.findById(id).orElse(null);
        if (theAuthor != null) {
            this.theAuthorRepository.delete(theAuthor);
        }
    }

}
