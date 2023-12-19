package com.example.jwt.domain.order;

import com.example.jwt.core.audit.UserAware;
import com.example.jwt.domain.country.Country;
import com.example.jwt.domain.order.dto.OrderMapper;
import com.example.jwt.domain.order.dto.OrderSummaryDTO;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.dto.UserDTO;
import com.example.jwt.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;
  private  final UserAware userAware;
  private final UserMapper userMapper;
  private final OrderMapper orderMapper;

  @Autowired
  public OrderController(OrderService orderService, UserAware userAware, UserMapper userMapper, OrderMapper orderMapper) {
    this.orderService = orderService;
    this.userAware = userAware;
    this.userMapper = userMapper;
    this.orderMapper = orderMapper;
  }

  //Aufgabe 4.1
  @GetMapping("/top-customer")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<UserDTO> getTopCustomer() {
    User topCustomer = orderService.findTopCustomer();
    return new ResponseEntity<>(userMapper.toDTO(topCustomer), HttpStatus.OK);
  }

  //Aufgabe 4.2
  @GetMapping("/top-country/{days}")
  public ResponseEntity<Country> getTopCountry(@PathVariable int days) {
    Country topCountry = orderService.findTopCountry(days);
    return new ResponseEntity<>(topCountry, HttpStatus.OK);
  }

  //Aufgabe 3
  @PostMapping("/{productName}/{amount}")
  @PreAuthorize("hasAuthority('CAN_PLACE_ORDER')")
  @ResponseBody
  public ResponseEntity<Order> placeOrder(@PathVariable String productName, @PathVariable int amount) {
    Order order = orderService.calculatePriceAndSeeds(productName, amount);
    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

//  @GetMapping("/order-history/")
//  @PreAuthorize("hasAuthority('CAN_RETRIEVE_PURCHASE_HISTORY')")
//  public ResponseEntity<String> getOrderHistory() {
//    String orderHistory = orderService.findTopCountry(days);
//    return new ResponseEntity<>(orderHistory, HttpStatus.OK);
//  }

  //@GetMapping("/order-history")
  //@PreAuthorize("hasAuthority('CAN_RETRIEVE_PURCHASE_HISTORY')")
  //public ResponseEntity<List<OrderDTO>> getOrderHistory(Authentication authentication) {
    // Hier wird angenommen, dass die UUID des Benutzers als 'username' im Authentication-Objekt gespeichert ist.
    //UUID userId = UUID.fromString(authentication.getName());
    //List<Order> orders = orderService.findAllOrderByUserID(userId);
    //List<OrderDTO> orderDTOs = orders.stream()
      //      .map(orderMapper::toDTO)
        //    .collect(Collectors.toList());
    //return ResponseEntity.ok(orderDTOs); // Verwenden von ResponseEntity.ok() um direkt den Status OK zu setzen
  //}
  @GetMapping("/order-history")
  @PreAuthorize("hasAuthority('CAN_RETRIEVE_PURCHASE_HISTORY')")
  public ResponseEntity<List<OrderSummaryDTO>> getOrderHistory() {
    List<OrderSummaryDTO> orderHistorySummary = orderService.getOrderSummaryForUser(userAware.getCurrentAuditor().get().getId());
    System.out.println("test");
    return ResponseEntity.ok(orderHistorySummary);
  }

}
