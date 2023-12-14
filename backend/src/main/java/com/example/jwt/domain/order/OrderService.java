package com.example.jwt.domain.order;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OrderService {

  User findTopCustomer();
  String findTopCountry(int days);
}
