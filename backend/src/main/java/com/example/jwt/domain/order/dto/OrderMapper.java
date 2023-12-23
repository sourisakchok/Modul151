package com.example.jwt.domain.order.dto;

import com.example.jwt.domain.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

  Order fromOrderDTO(OrderDTO dto);
  OrderDTO toOrderDTO(Order dto);
}
