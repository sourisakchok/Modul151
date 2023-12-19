package com.example.jwt.domain.order;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="orders")
public class Order extends ExtendedEntity {
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="user_id", nullable=false)
  @JsonIgnore
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="product_id", nullable=false)
  @JsonIgnore
  private Product product;

  @Column(name = "orderDate")
  private LocalDate orderDate;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "total")
  private double total;

  public Order() {
  }

  public Order(User user, Product product, LocalDate orderDate, int quantity, double total) {
    this.user = user;
    this.product = product;
    this.orderDate = orderDate;
    this.quantity = quantity;
    this.total = total;
  }

  public Order(UUID id, User user, Product product, LocalDate orderDate, int quantity, double total) {
    super(id);
    this.user = user;
    this.product = product;
    this.orderDate = orderDate;
    this.quantity = quantity;
    this.total = total;
  }

  public User getUser() {
    return user;
  }

  public Order setUser(User user) {
    this.user = user;
    return this;
  }

  public Product getProduct() {
    return product;
  }

  public Order setProduct(Product product) {
    this.product = product;
    return this;
  }

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public Order setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
    return this;
  }

  public int getQuantity() {
    return quantity;
  }

  public Order setQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }

  public double getTotal() {
    return total;
  }

  public Order setTotal(double total) {
    this.total = total;
    return this;
  }
}
