package com.tstu.model;

import com.tstu.model.enums.FilmType;
import com.tstu.utils.jpaConverters.DateConverter;
import com.tstu.utils.jpaConverters.FilmTypeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Film")
@Table(name = "films")
public class Film {

    @Id
    @Column(name = "imdbId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String imdbId;
    @Column(name = "type")
    @Convert(converter = FilmTypeConverter.class)
    private FilmType type;
    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "film_generes",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private List<Genre> genres;

    @Column(name = "releaseDate")
    @Convert(converter = DateConverter.class)
    private LocalDate releaseDate;

    @Transient
    private double rating = 0;

    @OneToMany(mappedBy = "film")
    private List<Review> reviews = new ArrayList<>();

    public Film() {
    }

    public Film(String imdbId, FilmType type, String name, List<Genre> genres, LocalDate releaseDate) {
        this.imdbId = imdbId;
        this.type = type;
        this.name = name;
        this.genres = genres;
        this.releaseDate = releaseDate;
    }

    public Film(String imdbId, FilmType type, String name, List<Genre> genres, LocalDate releaseDate, List<Review> reviews) {
        this.imdbId = imdbId;
        this.type = type;
        this.name = name;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.reviews = reviews;
        calculateAverageRating();
    }

    public void addReview(Review review) {
        reviews.add(review);
        calculateAverageRating();
    }

    public void addReviews(List<Review> reviews) {
        this.reviews.addAll(reviews);
        calculateAverageRating();
    }

    private void calculateAverageRating() {
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

    public List<Genre> getGenres() {
        return genres;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setType(FilmType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Film{" +
                "imdbId='" + imdbId + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", genres=" + genres +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
