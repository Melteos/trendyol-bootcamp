package com.trendyol.hotel.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class RoomTest {

    private Room createRoom(Hotel sut, int roomNumber) {
        return Room.builder()
                .id(UUID.randomUUID().toString())
                .hotelId(sut.getId())
                .roomNumber(roomNumber)
                .bookedDates(new ArrayList<Date>())
                .build();
    }

    private Hotel createHotel() {
        return Hotel.builder()
                .id(UUID.randomUUID().toString())
                .name("my hotel")
                .build();
    }

    private Date dateFromString(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    @Test
    public void checkAvailability_shouldReturnTrue_whenBookedDatesIsEmpty() throws ParseException {
        //Arrange
        Hotel hotel = createHotel();
        Room r1 = createRoom(hotel,1);
        Date beginDate = dateFromString("31/12/2020");
        Date endDate = dateFromString("31/12/2021");

        //Act
        boolean result = r1.checkAvailability(beginDate, endDate);

        //Assert
        assertTrue(result);
    }

    @Test
    public void checkAvailability_shouldReturnFalse_whenRoomIsNotAvailableBetweenGivenDates() throws ParseException {
        //Arrange
        Hotel hotel = createHotel();
        Room r1 = createRoom(hotel,1);
        Date beginDate = dateFromString("31/12/2020");
        Date endDate = dateFromString("31/12/2021");
        Date bookedDate = dateFromString("05/05/2021");
        List<Date> bookedDates = r1.getBookedDates();
        bookedDates.add(bookedDate);
        r1.setBookedDates(bookedDates);

        //Act
        boolean result = r1.checkAvailability(beginDate, endDate);

        //Assert
        assertFalse(result);
    }

    @Test
    public void checkAvailability_shouldReturnTrue_whenRoomIsAvailableBetweenGivenDates() throws ParseException {
        //Arrange
        Hotel hotel = createHotel();
        Room r1 = createRoom(hotel,1);
        Date beginDate = dateFromString("31/12/2020");
        Date endDate = dateFromString("31/12/2021");
        Date bookedDate = dateFromString("05/05/2020");
        List<Date> bookedDates = r1.getBookedDates();
        bookedDates.add(bookedDate);
        r1.setBookedDates(bookedDates);

        //Act
        boolean result = r1.checkAvailability(beginDate, endDate);

        //Assert
        assertTrue(result);
    }

}
