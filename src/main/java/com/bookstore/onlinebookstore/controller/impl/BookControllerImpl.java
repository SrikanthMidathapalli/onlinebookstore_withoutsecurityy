package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.controller.BookControllerInterface;
import com.bookstore.onlinebookstore.dto.BookDTO;
import com.bookstore.onlinebookstore.model.AuthorEntity;
import com.bookstore.onlinebookstore.model.BookEntity;
import com.bookstore.onlinebookstore.model.GenreEntity;
import com.bookstore.onlinebookstore.service.AuthorService;
import com.bookstore.onlinebookstore.service.BookService;
import com.bookstore.onlinebookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookControllerImpl implements BookControllerInterface {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Override
    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.findAllBooks();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/title/{title}")
    public List<BookEntity> getBooksByTitle(@PathVariable String title) {
        return bookService.findBooksByTitle(title);
    }

    @Override
    @GetMapping("/genre/{genre}")
    public List<BookEntity> getBooksByGenre(@PathVariable String genre) {
        return bookService.findBooksByGenre(genre);
    }

    @Override
    @GetMapping("/author/{author}")
    public List<BookEntity> getBooksByAuthor(@PathVariable String author) {
        return bookService.findBooksByAuthor(author);
    }

    @Override
    @PostMapping
    public BookEntity createBook(@RequestBody BookDTO bookDTO) {
        return createBook(convertToEntity(bookDTO));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        AuthorEntity author = authorService.findAuthorById(book.getAuthor().getId())
                                           .orElseThrow(() -> new RuntimeException("Author not found"));
        GenreEntity genre = genreService.findGenreById(book.getGenre().getId())
                                        .orElseThrow(() -> new RuntimeException("Genre not found"));

        book.setAuthor(author);
        book.setGenre(genre);

        return bookService.saveBook(book);
    }

    private BookEntity convertToEntity(BookDTO bookDTO) {
        BookEntity book = new BookEntity();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(new AuthorEntity()); // Set a placeholder, this will be replaced in createBook(BookEntity book)
        book.getAuthor().setId(bookDTO.getAuthorId());
        book.setGenre(new GenreEntity()); // Set a placeholder, this will be replaced in createBook(BookEntity book)
        book.getGenre().setId(bookDTO.getGenreId());
        book.setPrice(bookDTO.getPrice());
        book.setStock(bookDTO.getStock());
        book.setDescription(bookDTO.getDescription());
        return book;
    }
}
