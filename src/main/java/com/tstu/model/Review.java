package com.tstu.model;

import sun.util.resources.LocaleData;

import java.time.LocalDate;

public class Review {
    private int id;
    private static int nextId = 1;
    private User author;
    private LocalDate date;
    private String text;
    private int rating;

    public Review(User author, String text, int rating) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.date = LocalDate.now();
    }

    public Review(User author, String text, int rating,int id) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.date = LocalDate.now();
        this.id=id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDate getData() {
        return date;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }
}
