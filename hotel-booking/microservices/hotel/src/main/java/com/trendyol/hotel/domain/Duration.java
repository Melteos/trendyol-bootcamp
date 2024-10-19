package com.trendyol.hotel.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Duration {
    private Date startDate;
    private Date endDate;
}
