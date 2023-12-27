package com.example.jwt.domain.order.dto;

import com.example.jwt.domain.order.Order;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-27T21:12:35+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order fromOrderDTO(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( dto.getId() );
        order.setUser( dto.getUser() );
        order.setProduct( dto.getProduct() );
        if ( dto.getOrderDate() != null ) {
            order.setOrderDate( LocalDateTime.ofInstant( dto.getOrderDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        order.setQuantity( dto.getQuantity() );

        return order;
    }

    @Override
    public OrderDTO toOrderDTO(Order dto) {
        if ( dto == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( dto.getId() );
        orderDTO.setUser( dto.getUser() );
        orderDTO.setProduct( dto.getProduct() );
        if ( dto.getOrderDate() != null ) {
            orderDTO.setOrderDate( Date.from( dto.getOrderDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        orderDTO.setQuantity( dto.getQuantity() );

        return orderDTO;
    }
}
