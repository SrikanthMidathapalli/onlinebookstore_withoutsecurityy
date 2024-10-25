package com.bookstore.onlinebookstore.repository;

import com.bookstore.onlinebookstore.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByBook_Id(Long bookId);
}
