package com.projects.book.feedback;

import com.projects.book.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> book(Book book);

    @Query("""
            SELECT feedbacks
            from Feedback feedbacks
            WHERE feedbacks.book.id = :bookId
            """)
    Page<Feedback> findAllByBookId(Integer bookId, Pageable pageable);
}
