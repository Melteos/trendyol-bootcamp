package com.trendyol.hotel.domain;

import com.trendyol.hotel.exception.RoomNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class HotelTest {

    private Hotel sut;

    @BeforeEach
    private void beforeEach() {
        sut = createHotel();

        Room r1 = createRoom(sut, 1);
        Room r2 = createRoom(sut, 2);
        Room r3 = createRoom(sut, 3);

        List<Room> roomList = new ArrayList<>();
        roomList.add(r1);
        roomList.add(r2);
        roomList.add(r3);
        sut.setRooms(roomList);
    }

    @AfterEach
    public void afterEach() {
        sut = null;
    }

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
    public void getNoOfRooms_shouldReturnNumberOfRooms() {
        //Arrange

        //Act
        int result = sut.getNoOfRooms();

        //Assert
        assertEquals(3,result);
    }


    @Test
    public void findRoom_shouldReturnRoom_whenRoomIdExistsInHotelsRooms() {
        //Arrange
        Room r2 = sut.getRooms().get(1);

        //Act
        Room result = sut.findRoom(r2.getId());

        //Assert
        assertEquals(r2.getId(),result.getId());
    }

    @Test
    public void findRoom_shouldThrowException_whenRoomIdDoesNotExistsInHotelsRooms() {
        //Arrange

        //Act
        Throwable throwable = catchThrowable( () -> sut.findRoom("randomid") );

        //Assert
        assertThat(throwable).isInstanceOf(RoomNotFoundException.class).hasMessage("Room with id not found in the hotel.");
    }

    @Test
    public void getAvailableRooms_shouldReturnAvailableRoomsCount_whenThereAreNoBookedRooms() throws ParseException {
        //Arrange
        Date beginDate = dateFromString("31/12/2020");
        Date endDate = dateFromString("31/12/2021");

        //Act
        int result = sut.getNoOfAvailableRooms(beginDate, endDate);

        //Assert
        assertEquals(3,result);
    }

    @Test
    public void getAvailableRooms_shouldReturnAvailableRoomsCount_whenThereIsOneAmongThreeRooms() throws ParseException {
        //Arrange
        Date beginDate = dateFromString("31/12/2020");
        Date endDate = dateFromString("31/12/2021");
        Date bookedDate = dateFromString("05/05/2021");
        List<Date> bookedDates1 = sut.getRooms().get(0).getBookedDates();
        bookedDates1.add(bookedDate);
        sut.getRooms().get(0).setBookedDates(bookedDates1);
        List<Date> bookedDates2 = sut.getRooms().get(1).getBookedDates();
        bookedDates2.add(bookedDate);
        sut.getRooms().get(0).setBookedDates(bookedDates2);

        //Act
        int result = sut.getNoOfAvailableRooms(beginDate, endDate);

        //Assert
        assertEquals(1,result);
    }

}
