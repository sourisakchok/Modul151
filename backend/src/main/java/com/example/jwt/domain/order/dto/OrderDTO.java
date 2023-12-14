package com.example.jwt.domain.order.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.user.User;

import java.util.Date;
import java.util.UUID;

public class OrderDTO extends ExtendedDTO {

  private UUID id;
  private User user;
  private Product product;
  private Date orderDate;
  private int quantity;

  public OrderDTO() {
  }

  public OrderDTO(UUID id, User user, Product product, Date orderDate, int quantity) {
    super(id);
    this.user = user;
    this.product = product;
    this.orderDate = orderDate;
    this.quantity = quantity;
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public OrderDTO setId(UUID id) {
    this.id = id;
    return this;
  }

  public User getUser() {
    return user;
  }

  public OrderDTO setUser(User user) {
    this.user = user;
    return this;
  }

  public Product getProduct() {
    return product;
  }

  public OrderDTO setProduct(Product product) {
    this.product = product;
    return this;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public OrderDTO setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
    return this;
  }

  public int getQuantity() {
    return quantity;
  }

  public OrderDTO setQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
}
