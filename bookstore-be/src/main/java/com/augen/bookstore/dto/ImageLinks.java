package com.augen.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageLinks {
    private String smallThumbnail;
    private String thumbnail;
    private String small;
    private String medium;
    private String large;
}
