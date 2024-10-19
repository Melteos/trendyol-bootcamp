package com.trendyol.bootcamp.spotifyapishows.contract.response;

import com.trendyol.bootcamp.spotifyapishows.models.ImageModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EpisodeModel {
    private String audio_preview_url;
    private String description;
    private int duration_ms;
    private String id;
    private List<ImageModel> images;
    private List<String> languages;
    private String name;
    private String release_date;
    private String type;
}
