package com.trendyol.bootcamp.spotifyapishows.contract.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddEpisodeRequest {
    private String audio_preview_url;
    private String description;
    private String image_url;
    private List<String> languages;
    private String name;
}