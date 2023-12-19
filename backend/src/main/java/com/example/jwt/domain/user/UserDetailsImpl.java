package com.example.jwt.domain.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.jwt.domain.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record UserDetailsImpl(User user) implements UserDetails {

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (user.getRole() != null) {
      List<GrantedAuthority> authorities = new ArrayList<>();

      // Add the role as a GrantedAuthority
      authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));

      // Add authorities associated with the role
      authorities.addAll(
              user.getRole().getAuthorities().stream()
                      .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                      .collect(Collectors.toList())
      );

      return authorities;
    } else {
      return Collections.emptyList(); // No authorities if no role is assigned
    }
  }

  public String getRole() {
    return user.getRole().getName().toString();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
