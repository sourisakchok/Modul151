package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import com.example.jwt.domain.level.Level;
import com.example.jwt.domain.order.Order;
import com.example.jwt.domain.role.Role;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends ExtendedAuditEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "address")
  private String address;

  @Column(name = "ort")
  private String ort;

  @Column(name = "plz")
  private String plz;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "seeds_count")
  private int seeds_count;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "users_level", referencedColumnName = "id")
  private Level level;

//  @OneToOne(cascade = CascadeType.ALL)
//  @JoinColumn(name = "users_role", referencedColumnName = "id")
//  private Role role;

  @OneToMany(mappedBy = "user")
  private Set<Order> orders;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "users_role",
          joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();


  public User() {
  }

  public User(String firstName, String lastName, String email, String password, String address, String ort, String plz, LocalDate birthday, int seeds_count, Level level, Set<Role> roles, Set<Order> orders) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
    this.seeds_count = seeds_count;
    this.level = level;
    this.roles = roles;
    this.orders = orders;
  }

//  public User(UUID id, String firstName, String lastName, String email, String password, String address, String ort, String plz, LocalDate birthday, int seeds_count, Level level, Role role, Set<Order> orders) {
//    super(id);
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.email = email;
//    this.password = password;
//    this.address = address;
//    this.ort = ort;
//    this.plz = plz;
//    this.birthday = birthday;
//    this.seeds_count = seeds_count;
//    this.level = level;
//    this.role = role;
//    this.orders = orders;
//  }

  public String getFirstName() {
    return firstName;
  }

  public User setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public User setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public User setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getOrt() {
    return ort;
  }

  public User setOrt(String ort) {
    this.ort = ort;
    return this;
  }

  public String getPlz() {
    return plz;
  }

  public User setPlz(String plz) {
    this.plz = plz;
    return this;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public User setBirthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }

  public int getSeeds_count() {
    return seeds_count;
  }

  public User setSeeds_count(int seeds_count) {
    this.seeds_count = seeds_count;
    return this;
  }

  public Level getLevel() {
    return level;
  }

  public User setLevel(Level level) {
    this.level = level;
    return this;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public User setRoles(Set<Role> roles) {
    this.roles = roles;
    return this;
  }

  public Set<Order> getOrders() {
    return orders;
  }

  public User setOrders(Set<Order> orders) {
    this.orders = orders;
    return this;
  }
}