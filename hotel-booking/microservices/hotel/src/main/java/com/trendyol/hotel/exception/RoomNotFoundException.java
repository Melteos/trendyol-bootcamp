package com.trendyol.hotel.exception;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException() {
        super("Room with id not found in the hotel.");
    }
}
