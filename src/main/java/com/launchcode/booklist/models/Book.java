package com.launchcode.booklist.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, message = "Author name must not be empty")
    private String authorName;

    private int bookId;
    private static int nextId = 1;

    public Book(String name, String authorName) {
        this();
        this.name = name;
        this.authorName = authorName;
    }

    public Book() {
        bookId =  nextId;
        nextId++;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}


