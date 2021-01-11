package com.augen.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SearchParameters implements Serializable {
    private String keyword;
    private String searchType;
}
