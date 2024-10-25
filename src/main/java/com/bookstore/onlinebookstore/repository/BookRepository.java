package com.bookstore.onlinebookstore.repository;

import com.bookstore.onlinebookstore.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByTitleContaining(String title);
    List<BookEntity> findByGenre_Name(String genre);
    List<BookEntity> findByAuthor_Name(String author);
}
