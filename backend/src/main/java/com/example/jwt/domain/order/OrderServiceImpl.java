package com.example.jwt.domain.order;

import com.example.jwt.core.generic.ExtendedRepository;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.level.Level;
import com.example.jwt.domain.level.LevelRepository;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductService;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.RoleRepository;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImpl  extends ExtendedServiceImpl<Order> implements OrderService {

  private final OrderRepository orderRepository;
  private final LevelRepository levelRepository;

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  @Autowired
  public OrderServiceImpl(ExtendedRepository<Order> repository, Logger logger, OrderRepository orderRepository, LevelRepository levelRepository, UserRepository userRepository, RoleRepository roleRepository) {
    super(repository, logger);
    this.orderRepository = orderRepository;
    this.levelRepository = levelRepository;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }




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

  public Order calculatePriceAndSeeds(Order order) {
    // Annahme: Der Principal User ist der, der die Bestellung aufgibt.
    // Der 'principalUserId' sollte aus dem SecurityContext oder ähnlichem geholt werden.
    UUID principalUserId = UUID.randomUUID(); // Hier den aktuellen User ID abrufen

    User user = userRepository.findById(principalUserId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Level userLevel = levelRepository.findById(user.getLevel().getId())
            .orElseThrow(() -> new RuntimeException("Level not found"));

//    double discountRate = getDiscountRate(userLevel.getRank());
//    double totalPrice = order.getProduct().getPricePerGram() * order.getQuantity() * (1 - discountRate);
//    int seeds = (int)(totalPrice / 2); // 1 Seed pro 2 CHF
//
//    order.setTotalPrice(totalPrice);
//    order.setSeeds(seeds);

    return order;
  }
}

//  private double getDiscountRate(CustomerRank rank) {
//    // Rabatt aus der DB abrufen
//    Discount discount = discountRepository.findByRank(rank)
//            .orElseThrow(() -> new RuntimeException("Discount not found for rank: " + rank));
//    return discount.getRate();
//  }
//}
