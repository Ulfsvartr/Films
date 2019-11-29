package com.tstu.model;

import com.tstu.utils.jpaConverters.DateConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name="Review")
@Table(name = "reviews")
public class Review {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "author")
    //@Column(name = "author")
    private User author;
    @Column(name = "postDate")
    @Convert(converter = DateConverter.class)
    private LocalDate date;
    @Column(name = "text")
    private String text;
    @Column(name = "rating")
    private int rating;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public Review() {
    }

    public Review(User author, String text, int rating) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.date = LocalDate.now();
    }

    public Review(User author, String text,String date, int rating,long id) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date,dateFormatter);
        this.id=id;
    }

    public Review(User author, String text,String date, int rating,long id,Film film) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date,dateFormatter);
        this.id=id;
        this.film=film;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", film='" + film + '\'' +
                ", author='" + author.getUsername() + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
