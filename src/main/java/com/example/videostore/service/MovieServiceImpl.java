package com.example.videostore.service;

import com.example.videostore.model.Movie;
import com.example.videostore.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(String id, Movie updatedMovie) {
        if (movieRepository.existsById(id)) {
            updatedMovie.setId(id); // Ensure the ID is set
            return movieRepository.save(updatedMovie);
        } else {
            return null; // or throw an exception
        }
    }
    @Override
    public List<Movie> getTopMovies() {
        return movieRepository.findByTopMovieTrue();
    }
    @Override
    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Movie> getFeaturedMovies() {
        return movieRepository.findAllByFeaturedTrue();
    }
}
