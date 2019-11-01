package com.launchcode.booklist.models;

public enum BookRating {

    TOBEREAD ("To Be Read"),
    WILLREADAGAIN ("Great! Would Read Again!"),
    MIGHTREADAGAIN ("Might Read Again"),
    IREADIT ("Read it once, but won't again"),
    NEVERAGAIN ("I will never read it again!"),
    CANTDOIT ("No way - I can't finish it.");

    private final String name;

    BookRating(String name) { this.name = name; }

    public String getName() { return name; }
}
