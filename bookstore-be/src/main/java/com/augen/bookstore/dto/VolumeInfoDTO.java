package com.augen.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfoDTO {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private ImageLinks imageLinks;
    private String description;
}
