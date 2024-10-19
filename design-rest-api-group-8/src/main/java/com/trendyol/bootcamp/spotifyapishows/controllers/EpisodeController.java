package com.trendyol.bootcamp.spotifyapishows.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.trendyol.bootcamp.spotifyapishows.contract.request.AddEpisodeRequest;
import com.trendyol.bootcamp.spotifyapishows.contract.response.EpisodeModel;
import com.trendyol.bootcamp.spotifyapishows.contract.response.ShowResponse;
import com.trendyol.bootcamp.spotifyapishows.exception.EpisodeNotFoundException;
import com.trendyol.bootcamp.spotifyapishows.exception.ShowNotFoundException;
import com.trendyol.bootcamp.spotifyapishows.services.EpisodeService;
import com.trendyol.bootcamp.spotifyapishows.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/shows/{showId}/episodes")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping
    public ResponseEntity<List<EpisodeModel>> getAllEpisodes(@PathVariable String showId,
                                                             @RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "2") Integer pageSize) {
        List<EpisodeModel> episodes = episodeService.getAllEpisodesofAShow(showId, pageNo, pageSize);
        return ResponseEntity.ok(episodes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpisodeModel> getAEpisode(@PathVariable String showId, @PathVariable String id) {
        try {
            EpisodeModel episode = episodeService.getEpisodeById(showId, id);
            return ResponseEntity.ok(episode);
        }
        catch (EpisodeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAEpisode(@PathVariable String showId, @PathVariable String id) {
        try {
            episodeService.deleteEpisodebyId(showId, id);
            return ResponseEntity.noContent().build();
        }
        catch (EpisodeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createAEpisode(@PathVariable String showId, @RequestBody AddEpisodeRequest request) {
        EpisodeModel createdEpisode = episodeService.createEpisode(showId, request);
        URI location = URI.create(String.format("/shows/%s/episodes/%s", showId, createdEpisode.getId()));
        return ResponseEntity.created(location).build();
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity updateAEpisode(@PathVariable String showId, @PathVariable String id, @RequestBody JsonPatch patch) {
        try {
            EpisodeModel episode;
            try {
                episode = episodeService.getEpisodeById(showId, id);
            } catch (EpisodeNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
            EpisodeModel episodePatched = episodeService.applyPatchToEpisode(patch, episode);
            episodeService.updateEpisode(episodePatched);
            return ResponseEntity.ok(episodePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
