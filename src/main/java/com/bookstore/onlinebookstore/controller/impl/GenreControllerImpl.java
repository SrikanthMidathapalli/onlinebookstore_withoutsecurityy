package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.controller.GenreControllerInterface;
import com.bookstore.onlinebookstore.dto.GenreDTO;
import com.bookstore.onlinebookstore.model.GenreEntity;
import com.bookstore.onlinebookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreControllerImpl implements GenreControllerInterface {

    @Autowired
    private GenreService genreService;

    @Override
    @GetMapping
    public List<GenreEntity> getAllGenres() {
        return genreService.findAllGenres();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GenreEntity> getGenreById(@PathVariable Long id) {
        return genreService.findGenreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public GenreEntity createGenre(@RequestBody GenreEntity genre) {
        return genreService.saveGenre(genre);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<GenreEntity> updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.updateGenre(id, genreDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
