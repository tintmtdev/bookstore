package com.augen.bookstore.constants;

public enum SearchTypeEnum {
    GOOGLE("GOOGLE"),
    STORE("STORE"),
    JSON("JSON"),
    CSV("CSV");

    private final String type;

    SearchTypeEnum(String type) {
        this.type = type;
    }

    public String getType() { return this.type;}
}
