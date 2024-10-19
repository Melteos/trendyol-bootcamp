package com.trendyol.hotel.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.trendyol.hotel.contract.request.SearchRequest;
import com.trendyol.hotel.domain.Hotel;
import com.trendyol.hotel.service.hotel.HotelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelServiceImpl hotelServiceImpl;

    public HotelController(HotelServiceImpl hotelServiceImpl) {
        this.hotelServiceImpl = hotelServiceImpl;
    }
    /*
    hotels?blabla	GET	filtrelemeye göre otelleri getiren metod (search için)
    hotels	POST	otel create?
    hotels/id	GET	idye göre otel getirme
    */
    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addHotel(@RequestBody Hotel hotel) {
        try {
            URI location = URI.create(String.format("/hotels/%s", hotel.getId()));
            hotelServiceImpl.createHotel(hotel);
            return ResponseEntity.created(location).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Hotel> getHotelWithId(@PathVariable String id) {
        Optional<Hotel> hotel = hotelServiceImpl.getHotelByIdOptional(id);
        if(hotel.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(hotel.get());
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity patchHotel(@PathVariable String id, @RequestBody JsonPatch patch) {
        try {
            Optional<Hotel> optionalHotel = hotelServiceImpl.getHotelByIdOptional(id);
            if(optionalHotel.isEmpty())
                return ResponseEntity.notFound().build();
            Hotel hotel = optionalHotel.get();
            Hotel hotelPatched = hotelServiceImpl.applyPatchToHotel(patch, hotel);
            hotelServiceImpl.updateHotel(hotelPatched);
            return ResponseEntity.ok(hotelPatched);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
