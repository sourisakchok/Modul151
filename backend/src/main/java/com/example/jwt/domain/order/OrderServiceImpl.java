package com.example.jwt.domain.order;

import com.example.jwt.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Autowired
  public OrderServiceImpl(OrderRepository repository) {
    this.orderRepository = repository;
  }

  @Override
  public User findTopCustomer() {
    LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
    List<Order> orders = orderRepository.findOrdersBetweenDates(oneMonthAgo, LocalDate.now());

    // Gruppieren Sie die Bestellungen nach Benutzer und berechnen Sie den Gesamtumsatz für jeden Benutzer
    Map<User, Double> userSales = new HashMap<>();
    for (Order order : orders) {
      userSales.put(order.getUser(), userSales.getOrDefault(order.getUser(), 0.0) + order.getProduct().getSalePrice() * order.getQuantity());
    }

    // Finden Sie den Benutzer mit dem höchsten Gesamtumsatz
    User topCustomer = null;
    double maxSales = 0;
    for (Map.Entry<User, Double> entry : userSales.entrySet()) {
      if (entry.getValue() > maxSales) {
        topCustomer = entry.getKey();
        maxSales = entry.getValue();
      }
    }

    return topCustomer;
  }

  @Override
  public String findTopCountry(int days) {
    LocalDate startDate = LocalDate.now().minusDays(days);
    List<Order> orders = orderRepository.findOrdersBetweenDates(startDate, LocalDate.now());

    // Gruppieren Sie die Bestellungen nach dem Herkunftsland des Produkts und berechnen Sie die Gesamtmenge für jedes Land
    Map<String, Integer> countryOrders = new HashMap<>();
    for (Order order : orders) {
      countryOrders.put(order.getProduct().getOriginCountry(), countryOrders.getOrDefault(order.getProduct().getOriginCountry(), 0) + order.getQuantity());
    }

    // Finden Sie das Land mit der höchsten Gesamtmenge
    String topCountry = null;
    int maxOrders = 0;
    for (Map.Entry<String, Integer> entry : countryOrders.entrySet()) {
      if (entry.getValue() > maxOrders) {
        topCountry = entry.getKey();
        maxOrders = entry.getValue();
      }
    }

    return topCountry;
  }
}
