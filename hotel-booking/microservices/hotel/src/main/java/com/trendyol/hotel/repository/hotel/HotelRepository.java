package com.trendyol.hotel.repository.hotel;

import com.trendyol.hotel.domain.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelRepository {

	public void insert(Hotel hotel);

	public void update(Hotel hotel);

	public Optional<Hotel> findByIdOptional(String id);

	public List<Hotel> findAll();
}
