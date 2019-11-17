package com.tstu.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Review {
    private long id;
    private String author;
    private LocalDate date;
    private String text;
    private int rating;

    public Review(User author, String text, int rating) {
        this.author = author.getUsername();
        this.text = text;
        this.rating = rating;
        this.date = LocalDate.now();
    }

    public Review(String author, String text,String date, int rating,long id) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date,dateFormatter);
        this.id=id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
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
