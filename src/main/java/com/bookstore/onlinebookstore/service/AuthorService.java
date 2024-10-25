package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.AuthorDTO;
import com.bookstore.onlinebookstore.model.AuthorEntity;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorEntity> findAllAuthors();
    Optional<AuthorEntity> findAuthorById(Long id);
    AuthorEntity saveAuthor(AuthorEntity author);
    void deleteAuthor(Long id);
    AuthorEntity updateAuthor(Long id, AuthorDTO authorDTO);
}
