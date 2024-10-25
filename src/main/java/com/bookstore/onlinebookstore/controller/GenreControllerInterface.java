package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.GenreDTO;
import com.bookstore.onlinebookstore.model.GenreEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GenreControllerInterface {
    List<GenreEntity> getAllGenres();
    ResponseEntity<GenreEntity> getGenreById(@PathVariable Long id);
    GenreEntity createGenre(@RequestBody GenreEntity genre);
    ResponseEntity<GenreEntity> updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO);
    ResponseEntity<Void> deleteGenre(@PathVariable Long id);
}
