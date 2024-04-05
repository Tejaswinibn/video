package com.example.videostore.service;

import com.example.videostore.model.TVShow;
import com.example.videostore.repository.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TVShowServiceImpl implements TVShowService {

    @Autowired
    private TVShowRepository tvShowRepository;

    @Override
    public List<TVShow> getAllTVShows() {
        return tvShowRepository.findAll();
    }

    @Override
    public Optional<TVShow> getTVShowById(String id) {
        return tvShowRepository.findById(id);
    }

    @Override
    public TVShow createTVShow(TVShow tvShow) {
        return tvShowRepository.save(tvShow);
    }

    @Override
    public TVShow updateTVShow(String id, TVShow updatedTVShow) {
        if (tvShowRepository.existsById(id)) {
            updatedTVShow.setId(id); // Ensure the ID is set
            return tvShowRepository.save(updatedTVShow);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public void deleteTVShow(String id) {
        tvShowRepository.deleteById(id);
    }

    @Override
    public List<TVShow> searchTVShowsByTitle(String title) {
        return tvShowRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<TVShow> getFeaturedTVShows() {
        return tvShowRepository.findAllByFeaturedTrue();
    }
}
