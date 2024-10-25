package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.GenreDTO;
import com.bookstore.onlinebookstore.model.GenreEntity;
import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreEntity> findAllGenres();
    Optional<GenreEntity> findGenreById(Long id);
    GenreEntity saveGenre(GenreEntity genre);
    void deleteGenre(Long id);
    GenreEntity updateGenre(Long id, GenreDTO genreDTO);
}
