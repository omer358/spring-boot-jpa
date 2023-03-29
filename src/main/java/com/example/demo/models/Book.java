package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "book")
@Entity
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_id_sequence",
            sequenceName = "book_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "book_id_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "student",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "BOOK_ID_FK"

            )
    )
    private Student student;
    @Column(nullable = false)
    private String bookName;

    private LocalDate createdAt;

    public Book() {
    }

    public Book(Student studentId, String bookName, LocalDate createdAt) {
        this.student = studentId;
        this.bookName = bookName;
        this.createdAt = createdAt;
    }

    public Book(Long id, Student studentId, String bookName, LocalDate createdAt) {
        this.id = id;
        this.student = studentId;
        this.bookName = bookName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
