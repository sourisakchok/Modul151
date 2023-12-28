package com.example.jwt.domain.user.dto;

import com.example.jwt.domain.order.Order;
import com.example.jwt.domain.user.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-28T22:18:59+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromDTO(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setAddress( dto.getAddress() );
        user.setBirthday( dto.getBirthday() );
        user.setLevel( dto.getLevel() );
        user.setRole( dto.getRole() );
        user.setPlz( dto.getPlz() );
        Set<Order> set = dto.getOrders();
        if ( set != null ) {
            user.setOrders( new LinkedHashSet<Order>( set ) );
        }

        return user;
    }

    @Override
    public List<User> fromDTOs(List<UserDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( UserDTO userDTO : dtos ) {
            list.add( fromDTO( userDTO ) );
        }

        return list;
    }

    @Override
    public Set<User> fromDTOs(Set<UserDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<User> set = new LinkedHashSet<User>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( UserDTO userDTO : dtos ) {
            set.add( fromDTO( userDTO ) );
        }

        return set;
    }

    @Override
    public UserDTO toDTO(User BO) {
        if ( BO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( BO.getId() );
        userDTO.setFirstName( BO.getFirstName() );
        userDTO.setLastName( BO.getLastName() );
        userDTO.setEmail( BO.getEmail() );
        userDTO.setPassword( BO.getPassword() );
        userDTO.setAddress( BO.getAddress() );
        userDTO.setPlz( BO.getPlz() );
        userDTO.setBirthday( BO.getBirthday() );
        userDTO.setLevel( BO.getLevel() );
        userDTO.setRole( BO.getRole() );
        Set<Order> set = BO.getOrders();
        if ( set != null ) {
            userDTO.setOrders( new LinkedHashSet<Order>( set ) );
        }

        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOs(List<User> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( BOs.size() );
        for ( User user : BOs ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    @Override
    public Set<UserDTO> toDTOs(Set<User> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<UserDTO> set = new LinkedHashSet<UserDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( User user : BOs ) {
            set.add( toDTO( user ) );
        }

        return set;
    }
}
