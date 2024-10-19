package com.trendyol.hotel.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.trendyol.hotel.contract.request.SearchRequest;
import com.trendyol.hotel.domain.Hotel;
import com.trendyol.hotel.domain.Room;
import com.trendyol.hotel.service.hotel.HotelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
    hotels/id/rooms?	GET	filtrelemeye göre otelin odalarını getirme (müsaitlik vs)
    hotels/id/rooms	PATCH	otele oda ekleme

 */

@RestController
@RequestMapping("/hotels/{id}/rooms")
public class RoomController {

    private final HotelServiceImpl hotelServiceImpl;

    public RoomController(HotelServiceImpl hotelServiceImpl) {
        this.hotelServiceImpl = hotelServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAvailableRoomsOfHotel(@PathVariable String id,
                                                               @RequestBody SearchRequest searchRequest) {
        List<Room> rooms = hotelServiceImpl.getAvailableRoomsOfHotel(id, searchRequest);
        return ResponseEntity.ok(rooms);
    }

    @PatchMapping(path = "/{roomId}", consumes = "application/json-patch+json")
    public ResponseEntity patchHotel(@PathVariable String id, @PathVariable String roomId, @RequestBody JsonPatch patch) {
        try {
            Optional<Hotel> optionalHotel = hotelServiceImpl.getHotelByIdOptional(id);
            if(optionalHotel.isEmpty())
                return ResponseEntity.notFound().build();
            Hotel hotel = optionalHotel.get();
            Hotel hotelPatched = hotelServiceImpl.applyPatchToHotelRoom(patch, hotel, roomId);
            hotelServiceImpl.updateHotel(hotelPatched);
            return ResponseEntity.ok(hotelPatched);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
