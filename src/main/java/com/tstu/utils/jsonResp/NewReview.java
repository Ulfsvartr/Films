package com.tstu.utils.jsonResp;

public class NewReview {
    String text;
    Integer rating;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "NewReview{" +
                "text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }
}
