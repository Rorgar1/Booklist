package com.launchcode.booklist.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

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

    @ManyToOne
    private Optional<BookRating> bookRating;


    //private int bookId;
    //private static int nextId = 1;

    //private BookRating bookRating = BookRating.TOBEREAD;

    public Book(String name, String authorName) {
        //this();
        this.name = name;
        this.authorName = authorName;
    }

    public Book() {
       // bookId =  nextId;
        //nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    //public int getBookId() {return bookId;}
    //public void setBookId(int bookId) {this.bookId = bookId;}

    public Optional<BookRating> getBookRating() { return bookRating; }

    public void setBookRating(Optional<BookRating> bookRating) {this.bookRating = bookRating; }

    public int getId() { return id; }
}




