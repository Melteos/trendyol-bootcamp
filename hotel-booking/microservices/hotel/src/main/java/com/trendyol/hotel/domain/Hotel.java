package com.trendyol.hotel.domain;

import com.trendyol.hotel.exception.RoomNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Builder
public class Hotel {

    @NonNull
    private String id;
    @NonNull
    private String name;
    private Location location;
    private int noOfRooms;
    private List<Room> rooms;
    private int stars; //maybe enum
    private double rating;
    private String phone;
    private String email;
    private Set<Double> priceRange;

    public int getNoOfRooms() { return this.rooms.size(); }

    public Room findRoom(String id) {
        Optional<Room> room = rooms.stream().filter(r -> r.getId().equals(id) ).findAny();
        if(room.isEmpty())
            throw new RoomNotFoundException();
        return room.get();
    }

}
