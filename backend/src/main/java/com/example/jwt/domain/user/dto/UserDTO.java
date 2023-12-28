package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.level.Level;
import com.example.jwt.domain.order.Order;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.dto.RoleDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;

public class UserDTO extends ExtendedDTO {

  private String firstName;
  private String lastName;
  @Email
  private String email;
  private String password;
  private String address;
  private String ort;
  private String plz;
  private LocalDate birthday;
  private Level level;
  private Role role;
  private Set<Order> orders;

  public UserDTO() {
  }

  public UserDTO(String firstName, String lastName, String email, String password, String address, String ort, String plz, LocalDate birthday, Level level, Role role, Set<Order> orders) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
    this.level = level;
    this.role = role;
    this.orders = orders;
  }

  public UserDTO(UUID id, String firstName, String lastName, String email, String password, String address, String ort, String plz, LocalDate birthday, Level level, Role role, Set<Order> orders) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
    this.level = level;
    this.role = role;
    this.orders = orders;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserDTO setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getOrt() {
    return ort;
  }

  public UserDTO setOrt(String ort) {
    this.ort = ort;
    return this;
  }

  public String getPlz() {
    return plz;
  }

  public UserDTO setPlz(String plz) {
    this.plz = plz;
    return this;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public UserDTO setBirthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }

  public Level getLevel() {
    return level;
  }

  public UserDTO setLevel(Level level) {
    this.level = level;
    return this;
  }

  public Role getRole() {
    return role;
  }

  public UserDTO setRole(Role role) {
    this.role = role;
    return this;
  }

  public Set<Order> getOrders() {
    return orders;
  }

  public UserDTO setOrders(Set<Order> orders) {
    this.orders = orders;
    return this;
  }
}