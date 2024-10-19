package com.trendyol.hotel.service.hotel;

import com.trendyol.hotel.contract.request.SearchRequest;
import com.trendyol.hotel.domain.Hotel;
import com.trendyol.hotel.domain.Room;

import java.util.List;
import java.util.Optional;

public interface HotelService {

	public void createHotel(Hotel hotel);

	public void updateHotel(Hotel hotelPatched);

	public Optional<Hotel> getHotelByIdOptional(String id);

	public List<Room> getAvailableRoomsOfHotel(String id, SearchRequest searchRequest);
}
