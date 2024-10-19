package com.trendyol.bootcamp.spotifyapishows.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.trendyol.bootcamp.spotifyapishows.contract.request.AddEpisodeRequest;
import com.trendyol.bootcamp.spotifyapishows.contract.response.EpisodeModel;
import com.trendyol.bootcamp.spotifyapishows.contract.response.ShowResponse;
import com.trendyol.bootcamp.spotifyapishows.exception.EpisodeNotFoundException;
import com.trendyol.bootcamp.spotifyapishows.models.ImageModel;
import com.trendyol.bootcamp.spotifyapishows.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeService {

    @Autowired
    ShowRepository showRepository;

    ImageService imageService = new ImageService();

    public EpisodeModel getMockEpisodeData1() {

        List<ImageModel> images = new ArrayList<>();

        images.add(imageService.getMockEpisodeImageData160());
        images.add(imageService.getMockEpisodeImageData320());
        images.add(imageService.getMockEpisodeImageData640());

        List<String> languages = new ArrayList<>();

        languages.add("tr");
        languages.add("en");


        return EpisodeModel.builder()
                .audio_preview_url("https://p.scdn.co/mp3-preview/7a785904a33e34b0b2bd382c82fca16be7060c36")
                .description("This is a episode description")
                .duration_ms(2677448)
                .id("4d237GqKH4NP1jtgwy6bP3")
                .images(images)
                .languages(languages)
                .name("Episode Name")
                .release_date("2018-06-19")
                .type("episode")
                .build();
    }

    public EpisodeModel getMockEpisodeData2() {

        List<ImageModel> images = new ArrayList<>();

        images.add(imageService.getMockEpisodeImageData160());
        images.add(imageService.getMockEpisodeImageData320());
        images.add(imageService.getMockEpisodeImageData640());

        List<String> languages = new ArrayList<>();

        languages.add("sv");

        return EpisodeModel.builder()
                .audio_preview_url("https://p.scdn.co/mp3-preview/83bc7f2d40e850582a4ca118b33c256358de06ff")
                .description("Följ med Tobias Svanelid till Sveriges äldsta tegelkyrka")
                .duration_ms(2685023)
                .id("1spUiev4ggXPq95a7KKHjW")
                .images(images)
                .languages(languages)
                .name("Okända katedralen i Dalsland")
                .release_date("2019-09-03")
                .type("episode")
                .build();
    }

    public EpisodeModel getMockEpisodeDataWithId(String episodeId) {

        List<ImageModel> images = new ArrayList<>();

        images.add(imageService.getMockEpisodeImageData160());
        images.add(imageService.getMockEpisodeImageData320());
        images.add(imageService.getMockEpisodeImageData640());

        List<String> languages = new ArrayList<>();

        languages.add("tr");
        languages.add("en");


        return EpisodeModel.builder()
                .audio_preview_url("https://p.scdn.co/mp3-preview/7a785904a33e34b0b2bd382c82fca16be7060c36")
                .description("This is a episode description")
                .duration_ms(2677448)
                .id(episodeId)
                .images(images)
                .languages(languages)
                .name("Episode Name")
                .release_date("2018-06-19")
                .type("episode")
                .build();
    }

    public List<EpisodeModel> getAllEpisodesofAShow(String showId, int pageNo, int pageSize) {
        //paging yok
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<EpisodeModel> pagedResult = showRepository.findAll(QShowResponse.showResponse.id.eq(showId)).get(0).getEpisodes();
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<EpisodeModel>();
        }
    }

    public EpisodeModel getEpisodeById(String showId, String id) {
        List<EpisodeModel> episodes = showRepository.findAll(QShowResponse.showResponse.id.eq(showId)).get(0).getEpisodes();
        episodes = episodes.stream().filter(episode -> episode.getId().equals(id)).collect(Collectors.toList());
        if(episodes.size()==0)
            throw new EpisodeNotFoundException();
        return episodes.get(0);
    }

    public void deleteEpisodebyId(String showId, String id) {
        ShowResponse show = showRepository.findAll(QShowResponse.showResponse.id.eq(showId)).get(0);
        List<EpisodeModel> episodes = show.getEpisodes();
        episodes = episodes.stream().filter(episode -> episode.getId().equals(id)).collect(Collectors.toList());
        if(episodes.size()==0)
            throw new EpisodeNotFoundException();
        //could not delete, need a repo
        //another idea, create the same show without episode and delete old one, save new one
    }

    public EpisodeModel applyPatchToEpisode(JsonPatch patch, EpisodeModel targetEpisode) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetEpisode, JsonNode.class));
        return objectMapper.treeToValue(patched, EpisodeModel.class);
    }

    public void updateEpisode(EpisodeModel episodePatched) {
        //can not, same idea as delete
    }

    public EpisodeModel createEpisode(String showId, AddEpisodeRequest request) {
        //same idea
        return null;
    }
}
