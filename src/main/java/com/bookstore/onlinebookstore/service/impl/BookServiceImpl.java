package com.bookstore.onlinebookstore.service.impl;

import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.model.AuthorEntity;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.GenreEntity;
import com.bookstore.onlinebookstore.repository.AuthorRepository;
import com.bookstore.onlinebookstore.repository.BookRepository;
import com.bookstore.onlinebookstore.repository.GenreRepository;
import com.bookstore.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookEntity> findBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    @Override
    public List<BookEntity> findBooksByGenre(String genre) {
        return bookRepository.findByGenre_Name(genre);
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor_Name(author);
    }

    @Override
    public BookEntity saveBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookEntity updateBook(Long id, BookDTO bookDTO) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        AuthorEntity author = authorRepository.findById(bookDTO.getAuthorId())
                                .orElseThrow(() -> new RuntimeException("Author not found"));
        GenreEntity genre = genreRepository.findById(bookDTO.getGenreId())
                                .orElseThrow(() -> new RuntimeException("Genre not found"));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPrice(bookDTO.getPrice());
        book.setStock(bookDTO.getStock());
        book.setDescription(bookDTO.getDescription());

        return bookRepository.save(book);
    }

    private BookDTO convertToDTO(BookEntity book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthorId(book.getAuthor().getId());
        bookDTO.setGenreId(book.getGenre().getId());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setStock(book.getStock());
        bookDTO.setDescription(book.getDescription());
        return bookDTO;
    }

    private BookEntity convertToEntity(BookDTO bookDTO) {
        BookEntity book = new BookEntity();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setStock(bookDTO.getStock());
        book.setDescription(bookDTO.getDescription());
        return book;
    }
}
