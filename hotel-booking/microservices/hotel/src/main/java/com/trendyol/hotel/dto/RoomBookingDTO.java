package com.trendyol.hotel.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RoomBookingDTO {

	private String id;
	private String roomId;
	private Date startDate;
	private Date endDate;
	private Boolean isActive;
}
