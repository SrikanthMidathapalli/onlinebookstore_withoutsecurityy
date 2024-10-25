package com.bookstore.onlinebookstore.service.impl;

import com.bookstore.onlinebookstore.dto.AuthorDTO;
import com.bookstore.onlinebookstore.model.AuthorEntity;
import com.bookstore.onlinebookstore.repository.AuthorRepository;
import com.bookstore.onlinebookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<AuthorEntity> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<AuthorEntity> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public AuthorEntity saveAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorEntity updateAuthor(Long id, AuthorDTO authorDTO) {
        AuthorEntity author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());
        return authorRepository.save(author);
    }

    private AuthorDTO convertToDTO(AuthorEntity author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setBiography(author.getBiography());
        return authorDTO;
    }

    private AuthorEntity convertToEntity(AuthorDTO authorDTO) {
        AuthorEntity author = new AuthorEntity();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());
        return author;
    }
}
