package com.example.videostore.service;

import com.example.videostore.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAllMovies();
    Optional<Movie> getMovieById(String id);
    Movie createMovie(Movie movie);
    Movie updateMovie(String id, Movie updatedMovie);
    void deleteMovie(String id);
    List<Movie> searchMoviesByTitle(String title);
    List<Movie> getFeaturedMovies();
    List<Movie> getTopMovies();
}
