package com.example.jwt.domain.order.dto;

public class OrderSummaryDTO {
    private String teaType;
    private long totalQuantity;
    private long totalSeeds;

    // Konstruktor, Getter und Setter

    public OrderSummaryDTO(String teaType, long totalQuantity, long totalSeeds) {
        this.teaType = teaType;
        this.totalQuantity = totalQuantity;
        this.totalSeeds = totalSeeds;
    }

    public String getTeaType() {
        return teaType;
    }

    public void setTeaType(String teaType) {
        this.teaType = teaType;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public long getTotalSeeds() {
        return totalSeeds;
    }

    public void setTotalSeeds(long totalSeeds) {
        this.totalSeeds = totalSeeds;
    }
}

