package com.example.videostore.service;

import com.example.videostore.model.TVShow;

import java.util.List;
import java.util.Optional;

public interface TVShowService {
    List<TVShow> getAllTVShows();
    Optional<TVShow> getTVShowById(String id);
    TVShow createTVShow(TVShow tvShow);
    TVShow updateTVShow(String id, TVShow updatedTVShow);
    void deleteTVShow(String id);
    List<TVShow> searchTVShowsByTitle(String title);
    List<TVShow> getFeaturedTVShows();
}
