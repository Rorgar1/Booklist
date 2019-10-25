package com.launchcode.booklist.models;

public enum BookRating {

    ToBeRead ("To Be Read"),
    WILLREADAGAIN ("Will Read Again"),
    MIGHTREADAGAIN ("Might Read Again"),
    IREADIT ("I read it but won't do it again"),
    NEVERAGAIN ("I will never read it again"),
    CANTDOIT ("I can't finish it.");

    private final String name;

    BookRating(String name) { this.name = name; }

    public String getName() { return name; }
}
