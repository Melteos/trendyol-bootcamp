package com.trendyol.hotel.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Room {

    @NonNull
    private String id;
    @NonNull
    private String hotelId;
    @NonNull
    private int roomNumber;
    private int floor;
    private int noOfBeds;
    private double m2;
    private String view;
    private double nightlyPrice;
    private List<Duration> bookedDates = new ArrayList<Duration>(); //default value empty arraylist olcak

}
