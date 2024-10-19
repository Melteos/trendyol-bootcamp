package com.trendyol.bootcamp.spotifyapishows.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.trendyol.bootcamp.spotifyapishows.contract.request.AddShowRequest;
import com.trendyol.bootcamp.spotifyapishows.contract.response.ShowResponse;
import com.trendyol.bootcamp.spotifyapishows.exception.ShowNotFoundException;
import com.trendyol.bootcamp.spotifyapishows.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/shows")
public class ShowController {

    @Autowired
    private  ShowService showService;

    @GetMapping()
    public ResponseEntity<List<ShowResponse>> getAllShows(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String artistName,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize) {
        List<ShowResponse> shows;
        if(name==null && artistName==null)
            shows = showService.getAllShows(pageNo,pageSize);
        else
            shows = showService.getSeveralShows(name,artistName,pageNo,pageSize);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowResponse> getAShow(@PathVariable String id) {
        try{
            ShowResponse show = showService.getShowbyId(id);
            return ResponseEntity.ok(show);
        }
        catch (ShowNotFoundException s) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAShow(@PathVariable String id) {
        try {
            showService.deleteShowById(id);
            return ResponseEntity.noContent().build();
        }
        catch (ShowNotFoundException s) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createAShow(@RequestBody AddShowRequest request) {
        ShowResponse createdShow = showService.createAShow(request);

        URI location = URI.create(String.format("/shows/%s", createdShow.getId()));
        return ResponseEntity.created(location).build();
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity updateAShow(@PathVariable String id, @RequestBody JsonPatch patch) {
        try {
            ShowResponse show;
            try {
                show = showService.getShowbyId(id);
            }
            catch (ShowNotFoundException s) {
                return ResponseEntity.notFound().build();
            }
            ShowResponse showPatched = showService.applyPatchToShow(patch, show);
            showService.updateShow(showPatched);
            return ResponseEntity.ok(showPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /*
    @GetMapping
    public ResponseEntity<List<ShowResponse>> getTopShows() {
        // will be implemented
        return ResponseEntity.ok().build();
    }*/

    /*@GetMapping
    public ResponseEntity<List<ShowResponse>> getRelatedShows() {
        // will be implemented
        return ResponseEntity.ok().build();
    }*/

}
