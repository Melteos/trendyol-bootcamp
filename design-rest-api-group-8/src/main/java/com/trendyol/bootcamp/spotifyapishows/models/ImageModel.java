package com.trendyol.bootcamp.spotifyapishows.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageModel {
    private int height;
    private int width;
    private String url;
}
