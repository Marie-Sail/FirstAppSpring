package com.wildcodeschool.myproject.controller;

import com.wildcodeschool.myproject.dto.AuthorDTO;
import com.wildcodeschool.myproject.model.Author;
import com.wildcodeschool.myproject.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;

    }

    // GET
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthor() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        if(authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        AuthorDTO author = authorService.getAuthorById(id);
        if(author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    // Post
    @PostMapping
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody Author author) {
        AuthorDTO savedAuthor = authorService.createAuthor(author);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        AuthorDTO author = authorService.updateAuthor(id, authorDetails);
        if(author == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(author);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

        if (authorService.deleteAuthor(id)) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
