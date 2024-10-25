package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookEntity> findAllBooks();
    Optional<BookEntity> findBookById(Long id);
    List<BookEntity> findBooksByTitle(String title);
    List<BookEntity> findBooksByGenre(String genre);
    List<BookEntity> findBooksByAuthor(String author);
    BookEntity saveBook(BookEntity book);
    void deleteBook(Long id);
    BookEntity updateBook(Long id, BookDTO bookDTO);
}
