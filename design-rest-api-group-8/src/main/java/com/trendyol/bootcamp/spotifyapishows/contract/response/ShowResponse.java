package com.trendyol.bootcamp.spotifyapishows.contract.response;

import com.querydsl.core.annotations.QueryEntity;
import com.trendyol.bootcamp.spotifyapishows.models.ArtistModel;
import com.trendyol.bootcamp.spotifyapishows.models.ImageModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Builder
@QueryEntity
public class ShowResponse {
    private String description;
    private List<EpisodeModel> episodes;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private List<String> languages;
    private String name;
    private List<ImageModel> images;
    private ArtistModel artist;
    private String type;
}
