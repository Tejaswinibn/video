package com.example.videostore.controller;

import com.example.videostore.model.TVShow;
import com.example.videostore.service.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tvshows")
public class TVShowController {

    @Autowired
    private TVShowService tvShowService;

    @PostMapping
    public ResponseEntity<TVShow> createTVShow(@Valid @RequestBody TVShow tvShow) {
        TVShow createdTVShow = tvShowService.createTVShow(tvShow);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTVShow);
    }

    @GetMapping
    public ResponseEntity<List<TVShow>> getAllTVShows() {
        List<TVShow> tvShows = tvShowService.getAllTVShows();
        return ResponseEntity.ok(tvShows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TVShow> getTVShowById(@PathVariable String id) {
        Optional<TVShow> tvShow = tvShowService.getTVShowById(id);
        return tvShow.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TVShow> updateTVShow(@PathVariable String id, @Valid @RequestBody TVShow updatedTVShow) {
        TVShow tvShow = tvShowService.updateTVShow(id, updatedTVShow);
        if (tvShow != null) {
            return ResponseEntity.ok(tvShow);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTVShow(@PathVariable String id) {
        tvShowService.deleteTVShow(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TVShow>> searchTVShowsByTitle(@RequestParam String title) {
        List<TVShow> tvShows = tvShowService.searchTVShowsByTitle(title);
        return ResponseEntity.ok(tvShows);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<TVShow>> getFeaturedTVShows() {
        List<TVShow> featuredTVShows = tvShowService.getFeaturedTVShows();
        return ResponseEntity.ok(featuredTVShows);
    }
}
