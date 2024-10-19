package com.trendyol.bootcamp.spotifyapishows.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ArtistModel {
    private String name;
    private String id;
    private List<ImageModel> images;
}
