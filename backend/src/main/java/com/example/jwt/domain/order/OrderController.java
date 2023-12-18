package com.example.jwt.domain.order;

import com.example.jwt.domain.country.Country;
import com.example.jwt.domain.order.dto.OrderMapper;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.dto.UserDTO;
import com.example.jwt.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;
  private final UserMapper userMapper;
  private final OrderMapper orderMapper;

  @Autowired
  public OrderController(OrderService orderService, UserMapper userMapper, OrderMapper orderMapper) {
    this.orderService = orderService;
    this.userMapper = userMapper;
    this.orderMapper = orderMapper;
  }

  @GetMapping("/top-customer")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<UserDTO> getTopCustomer() {
    User topCustomer = orderService.findTopCustomer();
    return new ResponseEntity<>(userMapper.toDTO(topCustomer), HttpStatus.OK);
  }

  @GetMapping("/top-country/{days}")
  public ResponseEntity<Country> getTopCountry(@PathVariable int days) {
    Country topCountry = orderService.findTopCountry(days);
    return new ResponseEntity<>(topCountry, HttpStatus.OK);
  }

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
}
