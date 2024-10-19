package com.trendyol.bootcamp.spotifyapishows.contract.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddShowRequest {
    private String description;
    private List<String> languages;
    private String name;
    private String artist_id;
}