package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import com.example.jwt.domain.level.Level;
import com.example.jwt.domain.role.Role;

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
  @Column(name = "ort")
  private String ort;
  @Column(name = "plz")
  private String plz;
  @Column(name = "birthday")
  private LocalDate birthday;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "users_level", referencedColumnName = "id")
  private Level level;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "users_role",
          joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String firstName, String lastName, String email, String password, String address, String ort, String plz, LocalDate birthday, Level level, Set<Role> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
    this.level = level;
    this.roles = roles;
  }

  public User(UUID id, String firstName, String lastName, String email, String password, String address, String ort, String plz, LocalDate birthday, Level level, Set<Role> roles) {
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
    this.roles = roles;
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

  public void setAddress(String adresse) {
    this.address = address;
  }

  public String getOrt() {
    return ort;
  }

  public void setOrt(String ort) {
    this.ort = ort;
  }

  public String getPlz() {
    return plz;
  }

  public void setPlz(String plz) {
    this.plz = plz;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}