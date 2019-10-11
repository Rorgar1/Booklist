package com.launchcode.booklist.models;

import java.util.ArrayList;

public class BookData {

    static ArrayList<Book> books = new ArrayList<>();

    //get All
    public static ArrayList<Book> getAll() {
        return books;
    }

    //add
    public static void add(Book newBook) {
        books.add(newBook);
    }
//remove
    public static void remove(int id) {
        Book bookToRemove = getById(id);
        books.remove(bookToRemove);
    }
//getById
    public static Book getById(int id) {
        Book theBook = null;
        for (Book candidateBook : books) {
            if (candidateBook.getBookId() == id) {
                theBook = candidateBook;
            }
        }
        return theBook;
    }
}
