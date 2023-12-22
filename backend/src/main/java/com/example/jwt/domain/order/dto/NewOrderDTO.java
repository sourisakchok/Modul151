package com.example.jwt.domain.order.dto;

import java.time.LocalDate;

public class NewOrderDTO {

    private String teaName;

    private String teaType;
    private LocalDate orderDate;

    private double total;
    private int quantity;

    private int seedCount;

    public NewOrderDTO() {

    }

    public NewOrderDTO(String teaName, String teaType, LocalDate orderDate, double total, int quantity, int seedCount) {
        this.teaName = teaName;
        this.teaType = teaType;
        this.orderDate = orderDate;
        this.total = total;
        this.quantity = quantity;
        this.seedCount = seedCount;
    }

    public String getTeaName() {
        return teaName;
    }

    public NewOrderDTO setTeaName(String teaName) {
        this.teaName = teaName;
        return this;
    }

    public String getTeaType() {
        return teaType;
    }

    public NewOrderDTO setTeaType(String teaType) {
        this.teaType = teaType;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public NewOrderDTO setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public NewOrderDTO setTotal(double total) {
        this.total = total;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public NewOrderDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getSeedCount() {
        return seedCount;
    }

    public NewOrderDTO setSeedCount(int seedCount) {
        this.seedCount = seedCount;
        return this;
    }
}
