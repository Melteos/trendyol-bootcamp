package com.trendyol.hotel.repository.hotel;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.trendyol.hotel.domain.Hotel;
import com.trendyol.hotel.exception.HotelCouldNotBeInsertedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HotelRepositoryImpl implements HotelRepository{

    private final Cluster couchbaseCluster;
    private final Collection hotelCollection;

    @Override
    public void insert(Hotel hotel) {
        try {
            hotelCollection.insert(hotel.getId(), hotel);
        }
        catch (Exception e) {
            throw new HotelCouldNotBeInsertedException(e.getMessage());
        }
    }

    @Override
    public void update(Hotel hotel) { hotelCollection.replace(hotel.getId(), hotel); }

    @Override
    public Optional<Hotel> findByIdOptional(String id) {
        try {
            GetResult getResult = hotelCollection.get(id);
            Hotel hotel = getResult.contentAs(Hotel.class);
            return Optional.of(hotel);
        }
        catch (DocumentNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Hotel> findAll () {
        String statement = "Select id, name, location, noOfRooms, stars, rating, phone, email, priceRange from hotel";
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Hotel.class);
    }

}
