package com.tstu.repositoryJDBC;

public class FilmJDBC {
    private String imdbId;
    private String type;
    private String name;
    private String genre;
    private String releaseDate;

    public FilmJDBC(String imdbId, String type, String name, String genre, String releaseDate) {
        this.imdbId = imdbId;
        this.type = type;
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "FilmCSV{" +
                "imdbId='" + imdbId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
