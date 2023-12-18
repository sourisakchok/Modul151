package com.example.jwt.domain.order;

import com.example.jwt.core.audit.UserAware;
import com.example.jwt.core.generic.ExtendedRepository;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.country.Country;
import com.example.jwt.domain.level.Level;
import com.example.jwt.domain.level.LevelRepository;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import com.example.jwt.domain.role.RoleRepository;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImpl extends ExtendedServiceImpl<Order> implements OrderService {

    private final OrderRepository orderRepository;
    private final LevelRepository levelRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final UserAware userAware;

    @Autowired
    public OrderServiceImpl(ExtendedRepository<Order> repository, Logger logger, OrderRepository orderRepository, LevelRepository levelRepository, UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository, UserAware userAware) {
        super(repository, logger);
        this.orderRepository = orderRepository;
        this.levelRepository = levelRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.userAware = userAware;
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

    public Country findTopCountry(int days) {
    LocalDate startDate = LocalDate.now().minusDays(days);
    List<Order> orders = orderRepository.findOrdersBetweenDates(startDate, LocalDate.now());

    // Gruppieren Sie die Bestellungen nach dem Herkunftsland des Produkts und berechnen Sie die Gesamtmenge für jedes Land
    Map<Country, Integer> countryOrders = new HashMap<>();
    for (Order order : orders) {
      countryOrders.put(order.getProduct().getOriginCountry(), countryOrders.getOrDefault(order.getProduct().getOriginCountry(), 0) + order.getQuantity());
    }

    // Finden Sie das Land mit der höchsten Gesamtmenge
    Country topCountry = null;
    int maxOrders = 0;
    for (Map.Entry<Country, Integer> entry : countryOrders.entrySet()) {
      if (entry.getValue() > maxOrders) {
        topCountry = entry.getKey();
        maxOrders = entry.getValue();
      }
    }

    return topCountry;
    }

    @Override
    public List<Order> findAllOrderBYUserID(UUID UserID) {
        List<Order> orderHistory = orderRepository.findOrderByUserId(UserID);
        return null;
    }

    public Order calculatePriceAndSeeds(String productName, int amount) {
        // Annahme: Der Principal User ist der, der die Bestellung aufgibt.
        // Der 'principalUserId' sollte aus dem SecurityContext oder ähnlichem geholt werden.
        User user = userRepository.findByEmail(userAware.getCurrentAuditorEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Level userLevel = levelRepository.findById(user.getLevel().getId())
                .orElseThrow(() -> new RuntimeException("Level not found"));
        Product product = productRepository.findByName(productName);
        double totalPrice = product.getSalePrice() * (amount / 100) * (1 - userLevel.getDiscountRate());
        int seeds = (int) (totalPrice / 2); // 1 Seed pro 2 CHF
        int totalSeeds = user.getSeeds_count() + seeds;
        if (totalSeeds >= userLevel.getCountToUpgrade() && totalSeeds < levelRepository.findTopByOrderByCountToUpgradeDesc().getCountToUpgrade()) {
            // Finde das nächste Level im Reihenfolge
            Level nextLevel = findNextLevel(userLevel);
            // Aktualisiere das Level des Benutzers
            user.setLevel(nextLevel);
            // Überprüfe, ob der Benutzer das nächste Level auch übersprungen hat
            while (totalSeeds >= nextLevel.getCountToUpgrade()) {
                // Aktualisiere das Level des Benutzers weiter, bis das nächste Level erreicht ist
                user.setLevel(nextLevel);
                nextLevel = findNextLevel(nextLevel);
            }
        }
        user.setSeeds_count(totalSeeds);
        userRepository.save(user);
        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(amount);
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setTotal(Math.round(totalPrice * 100.0) / 100.0);
        orderRepository.save(order);
        return order;
    }

    private Level findNextLevel(Level currentLevel) {
        // Finde das nächste Level in der Reihenfolge
        List<Level> levels = levelRepository.findAll();
        int currentIndex = levels.indexOf(currentLevel);

        // Überprüfe, ob es ein nächstes Level gibt
        if (currentIndex < levels.size() - 1) {
            return levels.get(currentIndex + 1);
        } else {
            // Rückgabe des aktuellen Levels, wenn es das höchste Level ist
            return currentLevel;
        }
    }
}
