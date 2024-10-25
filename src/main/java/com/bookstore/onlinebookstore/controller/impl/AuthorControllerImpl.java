package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.controller.AuthorControllerInterface;
import com.bookstore.onlinebookstore.dto.AuthorDTO;
import com.bookstore.onlinebookstore.model.AuthorEntity;
import com.bookstore.onlinebookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorControllerImpl implements AuthorControllerInterface {

    @Autowired
    private AuthorService authorService;

    @Override
    @GetMapping
    public List<AuthorEntity> getAllAuthors() {
        return authorService.findAllAuthors();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable Long id) {
        return authorService.findAuthorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public AuthorEntity createAuthor(@RequestBody AuthorEntity author) {
        return authorService.saveAuthor(author);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AuthorEntity> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
