package com.example.jwt.domain.order;

import com.example.jwt.core.generic.ExtendedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends ExtendedRepository<Order> {

  @Query("SELECT o FROM Order o WHERE o.orderDate >= :startDate AND o.orderDate <= :endDate")
  List<Order> findOrdersBetweenDates(LocalDate startDate, LocalDate endDate);

//  @Query("SELECT o FROM Order o WHERE o.user_id = userId)
//  List<Order> findOrdersByUserId(userId);

  @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.orderDate >= :startDate AND o.orderDate <= :endDate")
  List<Order> findOrdersByUserAndBetweenDates(UUID userId, LocalDate startDate, LocalDate endDate);
}
