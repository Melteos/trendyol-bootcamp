package com.trendyol.hotel.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Location {
    private String country;
    private String city;
    private String district;
}
