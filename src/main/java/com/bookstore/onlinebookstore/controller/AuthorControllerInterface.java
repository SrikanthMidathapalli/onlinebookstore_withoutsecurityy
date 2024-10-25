package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.AuthorDTO;
import com.bookstore.onlinebookstore.model.AuthorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AuthorControllerInterface {
    List<AuthorEntity> getAllAuthors();
    ResponseEntity<AuthorEntity> getAuthorById(@PathVariable Long id);
    AuthorEntity createAuthor(@RequestBody AuthorEntity author);
    ResponseEntity<AuthorEntity> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO);
    ResponseEntity<Void> deleteAuthor(@PathVariable Long id);
}
