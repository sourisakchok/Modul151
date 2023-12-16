package com.example.jwt.domain.order;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface OrderService extends ExtendedService<Order>{

  User findTopCustomer();
  String findTopCountry(int days);


  List<Order> findAllOrderBYUserID(UUID UserID);
}
