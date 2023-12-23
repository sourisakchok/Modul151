package com.example.jwt.domain.order;

import com.example.jwt.core.audit.UserAware;
import com.example.jwt.domain.country.Country;
import com.example.jwt.domain.order.dto.NewOrderDTO;
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
  private final UserAware userAware;
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
  @GetMapping("/admin/top-customer")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserDTO> getTopCustomer() {
    User topCustomer = orderService.findTopCustomer();
    return new ResponseEntity<>(userMapper.toDTO(topCustomer), HttpStatus.OK);
  }

  //Aufgabe 4.2
  @GetMapping("/admin/top-country/{days}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Country> getTopCountry(@PathVariable int days) {
    Country topCountry = orderService.findTopCountry(days);
    return new ResponseEntity<>(topCountry, HttpStatus.OK);
  }

  //Aufgabe 3
  @PostMapping("/{productName}/{amount}")
  @PreAuthorize("hasAuthority('CAN_PLACE_ORDER')")
  //@ResponseBody
  public ResponseEntity<NewOrderDTO> placeOrder(@PathVariable String productName, @PathVariable int amount) {
    NewOrderDTO newOrderDTO = orderService.calculatePriceAndSeeds(productName, amount);
    return new ResponseEntity<>(newOrderDTO, HttpStatus.CREATED);
  }

  @GetMapping("/order-history")
  @PreAuthorize("hasAuthority('CAN_RETRIEVE_PURCHASE_HISTORY')")
  public ResponseEntity<List<OrderSummaryDTO>> getOrderHistory() {
    List<OrderSummaryDTO> orderHistorySummary = orderService.getOrderSummaryForUser();
    return new ResponseEntity<>(orderHistorySummary, HttpStatus.OK);
  }

}
