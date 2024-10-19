package com.trendyol.bootcamp.spotifyapishows.services;

import com.trendyol.bootcamp.spotifyapishows.models.ImageModel;

public class ImageService {

    public ImageModel getMockArtistImageData160() {
        return ImageModel.builder()
                .height(160)
                .width(160)
                .url("https://i.scdn.co/image/606eba074860660c91819636bcb5e141ddf4e23d")
                .build();
    }

    public ImageModel getMockArtistImageData320() {
        return ImageModel.builder()
                .height(320)
                .width(320)
                .url("https://i.scdn.co/image/606eba074860660c91819636bcb5e141ddf4e23d")
                .build();
    }

    public ImageModel getMockArtistImageData640() {
        return ImageModel.builder()
                .height(640)
                .width(640)
                .url("https://i.scdn.co/image/606eba074860660c91819636bcb5e141ddf4e23d")
                .build();
    }

    public ImageModel getMockEpisodeImageData160() {
        return ImageModel.builder()
                .height(160)
                .width(160)
                .url("https://i.scdn.co/image/2f4e58958061664dbe1666936dab319c70398d8b")
                .build();
    }

    public ImageModel getMockEpisodeImageData320() {
        return ImageModel.builder()
                .height(320)
                .width(320)
                .url("https://i.scdn.co/image/2f4e58958061664dbe1666936dab319c70398d8b")
                .build();
    }

    public ImageModel getMockEpisodeImageData640() {
        return ImageModel.builder()
                .height(640)
                .width(640)
                .url("https://i.scdn.co/image/2f4e58958061664dbe1666936dab319c70398d8b")
                .build();
    }

    public ImageModel getMockShowImageData160() {
        return ImageModel.builder()
                .height(160)
                .width(160)
                .url("https://i.scdn.co/image/c9976d5818a866de5607a61130c51ea1b4d59a62")
                .build();
    }

    public ImageModel getMockShowImageData320() {
        return ImageModel.builder()
                .height(320)
                .width(320)
                .url("https://i.scdn.co/image/c9976d5818a866de5607a61130c51ea1b4d59a62")
                .build();
    }

    public ImageModel getMockShowImageData640() {
        return ImageModel.builder()
                .height(640)
                .width(640)
                .url("https://i.scdn.co/image/c9976d5818a866de5607a61130c51ea1b4d59a62")
                .build();
    }
}
