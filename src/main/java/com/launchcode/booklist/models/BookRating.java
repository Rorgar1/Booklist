package com.launchcode.booklist.models;

import com.launchcode.booklist.models.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookRating {

/*public enum BookRating {

    TOBEREAD ("To Be Read"),
    WILLREADAGAIN ("Great! Would Read Again!"),
    MIGHTREADAGAIN ("Might Read Again"),
    IREADIT ("Read it once, but won't again"),
    NEVERAGAIN ("I will never read it again!"),
    CANTDOIT ("No way - I can't finish it."); */

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @JoinColumn(name = "bookRating_id")
    private List<Book> books = new ArrayList<>();

    public BookRating(){ }

    //public BookRating(String name) { this.name = name; }

    public int getId() { return id;}

    public String name;

    BookRating(String name) { this.name = name; }

    public String getName() { return name; }

    public List<Book> getBooks() { return books; }


}
