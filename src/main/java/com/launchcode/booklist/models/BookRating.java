package com.launchcode.booklist.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookRating {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @OneToMany
    @JoinColumn(name = "book_rating_id")
    private List<Book> books = new ArrayList<>();

    public BookRating() { }

    public BookRating(String name) { this.name = name; }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Book> getBooks() { return books; }


}
/*public enum BookRating {

    TOBEREAD ("To Be Read"),
    WILLREADAGAIN ("Great! Would Read Again!"),
    MIGHTREADAGAIN ("Might Read Again"),
    IREADIT ("Read it once, but won't again"),
    NEVERAGAIN ("I will never read it again!"),
    CANTDOIT ("No way - I can't finish it.");

    private final String name;

    BookRating(String name) { this.name = name; }

    public String getName() { return name; }
} */
