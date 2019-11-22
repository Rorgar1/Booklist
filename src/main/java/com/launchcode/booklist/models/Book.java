package com.launchcode.booklist.models;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, message = "Author name must not be empty")
    private String authorName;

    //private int bookId;
    //private static int nextId = 1;

    @ManyToOne
    private BookRating bookRating;

    @ManyToOne
    private User user;

    //private BookRating rating = BookRating.TOBEREAD;

    public Book(String name, String authorName) {
        //this();
        this.name = name;
        this.authorName = authorName;
    }

    public Book() { }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthorName() { return authorName; }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public BookRating getBookRating() { return bookRating; }

    public void setBookRating(BookRating bookRating) {this.bookRating = bookRating; }

}




