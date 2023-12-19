package com.example.jwt.domain.order;

import com.example.jwt.core.generic.ExtendedRepository;
import com.example.jwt.domain.order.dto.OrderSummaryDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends ExtendedRepository<Order> {

  @Query("SELECT o FROM Order o WHERE o.orderDate >= :startDate AND o.orderDate <= :endDate")
  List<Order> findOrdersBetweenDates(LocalDate startDate, LocalDate endDate);

  @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.orderDate >= :startDate AND o.orderDate <= :endDate")
  List<Order> findOrdersByUserAndBetweenDates(UUID userId, LocalDate startDate, LocalDate endDate);


  @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
          List<Order> findOrderByUserId(UUID userId);

  @Query("SELECT new com.example.jwt.domain.order.dto.OrderSummaryDTO(p.name, SUM(o.quantity), SUM(o.user.seeds_count)) " +
          "FROM Order o JOIN o.product p " +
          "WHERE o.user.id = :userId " +
          "GROUP BY p.name")
  List<OrderSummaryDTO> findOrderSummaryByUserId(@Param("userId") UUID userId);

}
