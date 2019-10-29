package com.tstu.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Film {
    private String imdbId;
    private FilmType type;
    private String name;
    private List<Genre> genre;
    private LocalDate releaseDate;

    private double rating=0;

    private List<Review> reviews = new ArrayList<>();

    public Film(String imdbId, FilmType type, String name, List<Genre> genre, LocalDate releaseDate) {
        this.imdbId = imdbId;
        this.type = type;
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public void addReview(Review review){
        reviews.add(review);
        calculateAverageRating();
    }

    public void calculateAverageRating(){
        rating = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getImdbId() {
        return imdbId;
    }

    public FilmType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

}
