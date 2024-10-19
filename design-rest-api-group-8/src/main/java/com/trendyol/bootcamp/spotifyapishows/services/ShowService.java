package com.trendyol.bootcamp.spotifyapishows.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.trendyol.bootcamp.spotifyapishows.contract.request.AddShowRequest;
import com.trendyol.bootcamp.spotifyapishows.contract.response.EpisodeModel;
import com.trendyol.bootcamp.spotifyapishows.contract.response.ShowResponse;
import com.trendyol.bootcamp.spotifyapishows.exception.ShowNotFoundException;
import com.trendyol.bootcamp.spotifyapishows.models.ArtistModel;
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
public class ShowService {

    @Autowired
    ShowRepository repository;

    ImageService imageService = new ImageService();
    EpisodeService episodeService = new EpisodeService();

    public ShowService() {
        repository.save(getMockShowData1());
        repository.save(getMockShowData2());
        repository.save(getMockShowData3());
    }

    private ShowResponse getMockShowData1() {
        List<EpisodeModel> episodes = new ArrayList<>();

        episodes.add(episodeService.getMockEpisodeData1());
        episodes.add(episodeService.getMockEpisodeData2());

        List<ImageModel> showImages = new ArrayList<>();

        showImages.add(imageService.getMockShowImageData160());
        showImages.add(imageService.getMockShowImageData320());
        showImages.add(imageService.getMockShowImageData640());

        List<String> languages = new ArrayList<>();

        languages.add("tr");
        languages.add("en");
        languages.add("sv");

        List<ImageModel> artistImages = new ArrayList<>();

        artistImages.add(imageService.getMockArtistImageData160());
        artistImages.add(imageService.getMockArtistImageData320());
        artistImages.add(imageService.getMockArtistImageData640());

        ArtistModel artist = ArtistModel.builder()
                .name("Band of Horses")
                .id("0OdUWJ0sBjDrqHygGUXeCF")
                .images(artistImages)
                .build();

        return ShowResponse.builder()
                .description("Hosted by Alex Blumberg, from Gimlet Media.")
                .episodes(episodes)
                .languages(languages)
                .name("Without Fail")
                .images(showImages)
                .artist(artist)
                .type("show")
                .build();
    }

    private ShowResponse getMockShowData2() {
        List<EpisodeModel> episodes = new ArrayList<>();

        episodes.add(episodeService.getMockEpisodeData1());

        List<ImageModel> showImages = new ArrayList<>();

        showImages.add(imageService.getMockShowImageData160());
        showImages.add(imageService.getMockShowImageData320());
        showImages.add(imageService.getMockShowImageData640());

        List<String> languages = new ArrayList<>();

        languages.add("tr");
        languages.add("en");

        List<ImageModel> artistImages = new ArrayList<>();

        artistImages.add(imageService.getMockArtistImageData160());
        artistImages.add(imageService.getMockArtistImageData320());
        artistImages.add(imageService.getMockArtistImageData640());

        ArtistModel artist = ArtistModel.builder()
                .name("David Bowie")
                .id("3dBVyJ7JuOMt4GE9607Qin")
                .images(artistImages)
                .build();

        return ShowResponse.builder()
                .description("Giant Bomb discusses the latest video game news and new releases.")
                .episodes(episodes)
                .languages(languages)
                .name("Giant Bombcast")
                .images(showImages)
                .artist(artist)
                .type("show")
                .build();
    }

    private ShowResponse getMockShowData3() {
        List<EpisodeModel> episodes = new ArrayList<>();

        episodes.add(episodeService.getMockEpisodeData2());

        List<ImageModel> showImages = new ArrayList<>();

        showImages.add(imageService.getMockShowImageData160());
        showImages.add(imageService.getMockShowImageData320());
        showImages.add(imageService.getMockShowImageData640());

        List<String> languages = new ArrayList<>();

        languages.add("sv");

        List<ImageModel> artistImages = new ArrayList<>();

        artistImages.add(imageService.getMockArtistImageData160());
        artistImages.add(imageService.getMockArtistImageData320());
        artistImages.add(imageService.getMockArtistImageData640());

        ArtistModel artist = ArtistModel.builder()
                .name("T. Rex")
                .id("0oSGxfWSnnOXhD2fKuz2Gy")
                .images(artistImages)
                .build();

        return ShowResponse.builder()
                .description("Hosted by Alex Blumberg, from Gimlet Media.")
                .episodes(episodes)
                .languages(languages)
                .name("Giant Bombcast")
                .images(showImages)
                .artist(artist)
                .type("show")
                .build();
    }

    public ShowResponse getMockShowDataWithId(String showId) {
        List<EpisodeModel> episodes = new ArrayList<>();

        episodes.add(episodeService.getMockEpisodeData1());
        episodes.add(episodeService.getMockEpisodeData2());

        List<ImageModel> showImages = new ArrayList<>();

        showImages.add(imageService.getMockShowImageData160());
        showImages.add(imageService.getMockShowImageData320());
        showImages.add(imageService.getMockShowImageData640());

        List<String> languages = new ArrayList<>();

        languages.add("tr");
        languages.add("en");
        languages.add("sv");

        List<ImageModel> artistImages = new ArrayList<>();

        artistImages.add(imageService.getMockArtistImageData160());
        artistImages.add(imageService.getMockArtistImageData320());
        artistImages.add(imageService.getMockArtistImageData640());

        ArtistModel artist = ArtistModel.builder()
                .name("Band of Horses")
                .id("0OdUWJ0sBjDrqHygGUXeCF")
                .images(artistImages)
                .build();

        return ShowResponse.builder()
                .description("Hosted by Alex Blumberg, from Gimlet Media.")
                .episodes(episodes)
                .id(showId)
                .languages(languages)
                .name("Without Fail")
                .images(showImages)
                .artist(artist)
                .type("show")
                .build();
    }

    public List<ShowResponse> getAllShows(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ShowResponse> pagedResult = repository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<ShowResponse>();
        }
    }

    public ShowResponse getShowbyId(String id) {
        try {
            return repository.findAll(QShowResponse.showResponse.id.eq(id)).get(0);
        }
        catch (Exception e) {
            throw new ShowNotFoundException();
        }
    }

    public List<ShowResponse> getSeveralShows(String name, String artistName, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        //artistname hatalı
        Page<ShowResponse> pagedResult = repository.findAll(QShowResponse.showResponse.name.eq(name).and(QShowResponse.showResponse.artist.eq(artistName)));
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<ShowResponse>();
        }

    }

    public void deleteShowById(String id) {
        try {
            repository.delete(repository.findAll(QShowResponse.showResponse.id.eq(id)).get(0));
        }
        catch(Exception e){
            throw new ShowNotFoundException();
        }
    }

    public ShowResponse createAShow(AddShowRequest request) {
        ShowResponse show = ShowResponse.builder()
                                .description(request.getDescription())
                                .languages(request.getLanguages())
                                .artist(ArtistModel.builder()
                                        .id(request.getArtist_id())
                                        .build())
                                .name(request.getName())
                                .id(repository.findAll().iterator().next().getId() + "A")
                                .type("show")
                                .build();
        repository.save(show);
        return show;
    }

    public ShowResponse applyPatchToShow(JsonPatch patch, ShowResponse targetShow) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetShow, JsonNode.class));
        return objectMapper.treeToValue(patched, ShowResponse.class);
    }

    public void updateShow(ShowResponse showPatched) {
        repository.save(showPatched);
    }
}
