package com.tstu.model.dto;

import java.util.ArrayList;
import java.util.List;

public class FilmDto {
    private String imdbId;
    private String type;
    private String name;
    private List<GenreDto> genres;
    private String releaseDate;
    private String rating;

    private List<ReviewDto> reviews;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public String toString() {
        return "FilmDto{" +
                "imdbId='" + imdbId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", genres='" + genres + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

}
