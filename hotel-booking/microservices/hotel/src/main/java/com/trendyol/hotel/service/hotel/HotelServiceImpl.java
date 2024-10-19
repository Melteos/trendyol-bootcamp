package com.trendyol.hotel.service.hotel;

import com.github.fge.jsonpatch.JsonPatch;
import com.trendyol.hotel.contract.request.SearchRequest;
import com.trendyol.hotel.domain.Hotel;
import com.trendyol.hotel.domain.Room;
import com.trendyol.hotel.exception.HotelNotFoundException;
import com.trendyol.hotel.repository.hotel.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public Hotel applyPatchToHotelRoom(JsonPatch patch, Hotel hotel, String roomId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateHotel(Hotel hotelPatched) {
    }

    public Hotel applyPatchToHotel(JsonPatch patch, Hotel hotel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Hotel> getHotelByIdOptional(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createHotel(Hotel hotel) {
        hotelRepository.insert(hotel);
    }

    @Override
    public List<Room> getAvailableRoomsOfHotel(String id, SearchRequest searchRequest) {
        Optional<Hotel> hotel = hotelRepository.findByIdOptional(id);
        if(hotel.isEmpty())
            throw new HotelNotFoundException();
        Date beginDate;
        Date endDate;
        try {
            beginDate = new SimpleDateFormat("dd/MM/yyyy").parse(searchRequest.getBeginDate());
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse(searchRequest.getEndDate());
        }
        catch (ParseException e) {
            System.out.println("Could not parse given dates.");
            return null;
        }
        List<Room> availableRooms;
        //TODO indexleme ile available roomları getirmece repodan
        //TODO priceı da bu metodlara ekleme, otelin noOfAvailableRoomsını set edip oteli update etme, boş liste durumu
        return availableRooms;
    }
}
