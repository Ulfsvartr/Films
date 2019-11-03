package com.tstu.exceptions;

public class MovieLibraryException  extends  Exception{
    public MovieLibraryException(MovieLibraryError error) {
        super(error.getValue());
    }
}
