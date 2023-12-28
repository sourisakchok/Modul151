package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import com.example.jwt.domain.level.Level;
import com.example.jwt.domain.order.Order;
import com.example.jwt.domain.plz.Plz;
import com.example.jwt.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "seeds_count")
  private int seeds_count;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "users_level", referencedColumnName = "id")
  private Level level;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "users_role", referencedColumnName = "id")
  private Role role;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "plz", referencedColumnName = "plz")
  private Plz plz;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  private Set<Order> orders;

//  @ManyToMany(fetch = FetchType.EAGER)
//  @JoinTable(
//          name = "users_role",
//          joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
//          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//  private Set<Role> roles = new HashSet<>();


  public User() {
  }


  public User(String firstName, String lastName, String email, String password, String address, LocalDate birthday, int seeds_count, Level level, Role role, Plz plz, Set<Order> orders) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.birthday = birthday;
    this.seeds_count = seeds_count;
    this.level = level;
    this.role = role;
    this.plz = plz;
    this.orders = orders;
  }

  public User(UUID id, String firstName, String lastName, String email, String password, String address, LocalDate birthday, int seeds_count, Level level, Role role, Plz plz, Set<Order> orders) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.birthday = birthday;
    this.seeds_count = seeds_count;
    this.level = level;
    this.role = role;
    this.plz = plz;
    this.orders = orders;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public int getSeeds_count() {
    return seeds_count;
  }

  public void setSeeds_count(int seeds_count) {
    this.seeds_count = seeds_count;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Plz getPlz() {
    return plz;
  }

  public void setPlz(Plz plz) {
    this.plz = plz;
  }

  public Set<Order> getOrders() {
    return orders;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }

}