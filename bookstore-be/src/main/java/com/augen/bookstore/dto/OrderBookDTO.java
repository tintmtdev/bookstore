package com.augen.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookDTO {
    private String shipBy;
    private String deliveryNumber;
    private String deliveryName;
    private Date dateDelivery;
    private Double totalCost;
}
