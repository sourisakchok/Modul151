package com.example.jwt.domain.order.dto;

public class OrderSummaryDTO {
    private String teaType;
    private long totalProducts;
    private long totalQuantity;
    private long totalSeeds;

    // Konstruktor, Getter und Setter

    public OrderSummaryDTO(String teaType, long totalProducts, long totalQuantity, long totalSeeds) {
        this.teaType = teaType;
        this.totalProducts = totalProducts;
        this.totalQuantity = totalQuantity;
        this.totalSeeds = totalSeeds;
    }

    public String getTeaType() {
        return teaType;
    }

    public OrderSummaryDTO setTeaType(String teaType) {
        this.teaType = teaType;
        return this;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public OrderSummaryDTO setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
        return this;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public OrderSummaryDTO setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }

    public long getTotalSeeds() {
        return totalSeeds;
    }

    public OrderSummaryDTO setTotalSeeds(long totalSeeds) {
        this.totalSeeds = totalSeeds;
        return this;
    }
}

