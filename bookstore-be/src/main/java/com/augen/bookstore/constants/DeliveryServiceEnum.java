package com.augen.bookstore.constants;

public enum DeliveryServiceEnum {
    MOTORBIKE("MotorBike", 5.0, 0.5, 1.5, 1.0),
    TRAIN("Train", 10.0, 0.8, 1.8, 1.0),
    AIRCRAFT("AirCraft", 20.0, 0.8, 2.0, 1.0);

    private final String type;
    private final Double cost;
    private final Double juneToAug;
    private final Double sep;
    private final Double otherMonth;

    DeliveryServiceEnum(String type, Double cost, Double juneToAug, Double sep, Double otherMonth) {
        this.type = type;
        this.cost = cost;
        this.juneToAug = juneToAug;
        this.sep = sep;
        this.otherMonth = otherMonth;
    }

    public String getType() { return this.type;}
    public Double getCost() {return this.cost;}
    public Double getJuneToAug() {return this.juneToAug;}
    public Double getSep() {return this.sep;}
    public Double getOtherMonth() {return this.otherMonth;}
}
