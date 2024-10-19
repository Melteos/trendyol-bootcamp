package com.trendyol.hotel.contract.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Getter
@Setter
public class SearchRequest {
    private int pageNo;
    private int pageSize;
    private String beginDate;
    @NonNull
    private String endDate;
    private double lowPriceLimit;
    private double highPriceLimit;

    public SearchRequest() {
        this.pageNo = 0;
        this.pageSize = 10;
        this.beginDate = new Date().toString();
        this.endDate = new Date(System.currentTimeMillis()*2).toString(); //27.06.2071
        this.lowPriceLimit = 0;
        this.highPriceLimit = Double.MAX_VALUE;
    }
}
