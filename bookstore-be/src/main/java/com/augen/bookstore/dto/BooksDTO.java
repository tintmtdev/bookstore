package com.augen.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksDTO {
    private String id;
    private String selfLink;
    private VolumeInfoDTO volumeInfo;
    private List<DeliveryServiceDTO> deliveryServices;
}
