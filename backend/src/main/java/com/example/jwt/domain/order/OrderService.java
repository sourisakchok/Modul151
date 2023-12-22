package com.example.jwt.domain.order;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.country.Country;
import com.example.jwt.domain.order.dto.NewOrderDTO;
import com.example.jwt.domain.order.dto.OrderSummaryDTO;
import com.example.jwt.domain.user.User;
import java.util.List;
import java.util.UUID;

public interface OrderService extends ExtendedService<Order>{

  User findTopCustomer();
  Country findTopCountry(int days);


  List<Order> findAllOrderByUserID(UUID UserID);

  NewOrderDTO calculatePriceAndSeeds(String productName, int amount);
  List<OrderSummaryDTO> getOrderSummaryForUser();

}
