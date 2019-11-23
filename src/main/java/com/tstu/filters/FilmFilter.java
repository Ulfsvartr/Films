package com.tstu.filters;

public class FilmFilter {
    Integer fromYear;
    Integer toYear;
    Double fromRating;
    Double toRating;

    public Integer getFromYear() {
        return fromYear;
    }

    public void setFromYear(Integer fromYear) {
        this.fromYear = fromYear;
    }

    public Integer getToYear() {
        return toYear;
    }

    public void setToYear(Integer toYear) {
        this.toYear = toYear;
    }

    public Double getFromRating() {
        return fromRating;
    }

    public void setFromRating(Double fromRating) {
        this.fromRating = fromRating;
    }

    public Double getToRating() {
        return toRating;
    }

    public void setToRating(Double toRating) {
        this.toRating = toRating;
    }

    @Override
    public String toString() {
        return "FilmFilter{" +
                "fromYear='" + fromYear + '\'' +
                ", toYear='" + toYear + '\'' +
                ", fromRating='" + fromRating + '\'' +
                ", toRating='" + toRating + '\'' +
                '}';
    }
}
