package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.model.BookEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookControllerInterface {
    List<BookEntity> getAllBooks();
    ResponseEntity<BookEntity> getBookById(@PathVariable Long id);
    List<BookEntity> getBooksByTitle(@PathVariable String title);
    List<BookEntity> getBooksByGenre(@PathVariable String genre);
    List<BookEntity> getBooksByAuthor(@PathVariable String author);
    BookEntity createBook(@RequestBody BookEntity book);
    ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO);
    ResponseEntity<Void> deleteBook(@PathVariable Long id);
	BookEntity createBook(BookDTO bookDTO);
	
}
