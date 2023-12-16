package com.example.jwt.domain.order;

import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.dto.UserDTO;
import com.example.jwt.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;
  private final UserMapper userMapper;

  @Autowired
  public OrderController(OrderService orderService, UserMapper userMapper) {
    this.orderService = orderService;
    this.userMapper = userMapper;
  }

  @GetMapping("/top-customer")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<UserDTO> getTopCustomer() {
    User topCustomer = orderService.findTopCustomer();
    return new ResponseEntity<>(userMapper.toDTO(topCustomer), HttpStatus.OK);
  }

  @GetMapping("/top-country/{days}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<String> getTopCountry(@PathVariable int days) {
    String topCountry = orderService.findTopCountry(days);
    return new ResponseEntity<>(topCountry, HttpStatus.OK);
  }

//  @GetMapping("/order-history/")
//  @PreAuthorize("hasAuthority('CAN_RETRIEVE_PURCHASE_HISTORY')")
//  public ResponseEntity<String> getOrderHistory() {
//    String orderHistory = orderService.findTopCountry(days);
//    return new ResponseEntity<>(orderHistory, HttpStatus.OK);
//  }
}
