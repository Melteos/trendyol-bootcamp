package com.trendyol.hotel.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RoomBooking {
	private String id;
	private String roomId;
	private Date startDate;
	private Date endDate;
	private Boolean isActive;
}
