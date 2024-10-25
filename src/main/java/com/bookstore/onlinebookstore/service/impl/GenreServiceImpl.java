package com.bookstore.onlinebookstore.service.impl;

import com.bookstore.onlinebookstore.dto.GenreDTO;
import com.bookstore.onlinebookstore.model.GenreEntity;
import com.bookstore.onlinebookstore.repository.GenreRepository;
import com.bookstore.onlinebookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreEntity> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<GenreEntity> findGenreById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public GenreEntity saveGenre(GenreEntity genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public GenreEntity updateGenre(Long id, GenreDTO genreDTO) {
        GenreEntity genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found"));
        genre.setName(genreDTO.getName());
        return genreRepository.save(genre);
    }

    private GenreDTO convertToDTO(GenreEntity genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    private GenreEntity convertToEntity(GenreDTO genreDTO) {
        GenreEntity genre = new GenreEntity();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }
}
