package com.trendyol.hotel.exception;

public class HotelCouldNotBeInsertedException extends RuntimeException {

    public HotelCouldNotBeInsertedException(String message){
        super(message);
    }
}
