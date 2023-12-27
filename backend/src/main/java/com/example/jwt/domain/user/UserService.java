package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService, ExtendedService<User> {

  User register(User user);

Optional<User> retrievePrincipal();

}
