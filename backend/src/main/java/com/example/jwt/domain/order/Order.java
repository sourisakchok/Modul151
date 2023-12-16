package com.example.jwt.domain.order;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="orders")
public class Order extends ExtendedEntity {
  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;

  @ManyToOne
  @JoinColumn(name="product_id", nullable=false)
  private Product product;

  @Column(name = "orderDate")
  private Date orderDate;

  @Column(name = "quantity")
  private int quantity;

  public Order() {
  }

  public Order(User user, Product product, Date orderDate, int quantity) {
    this.user = user;
    this.product = product;
    this.orderDate = orderDate;
    this.quantity = quantity;
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

  public Date getOrderDate() {
    return orderDate;
  }

  public Order setOrderDate(Date orderDate) {
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
}
