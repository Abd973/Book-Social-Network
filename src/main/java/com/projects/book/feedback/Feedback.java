package com.projects.book.feedback;

import com.projects.book.book.Book;
import com.projects.book.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntity {

    private double note; //1-5

    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
