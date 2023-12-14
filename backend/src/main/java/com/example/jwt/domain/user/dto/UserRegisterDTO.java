package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;

public class UserRegisterDTO extends ExtendedDTO {

  @NotBlank(message = "{validation.firstname.notblank}")
  private String firstName;

  @NotBlank(message = "{validation.lastname.notblank}")
  private String lastName;

  @NotBlank(message = "{validation.email.notblank}")
  @Email(message = "{validation.email.email}")
  private String email;

  @NotBlank(message = "{validation.password.notblank}")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$",
          message = "{validation.password.pattern}")
  private String password;

  @NotBlank(message = "{validation.address.notblank}")
  private String address;

  @NotBlank(message = "{validation.ort.notblank}")
  private String ort;

  @NotBlank(message = "{validation.plz.notblank}")
  @Pattern(regexp = "\\d+", message = "{validation.plz.pattern}")
  private String plz;

  @NotNull(message = "{validation.birthday.notnull}")
  @Past(message = "{validation.birthday.past}")
  private LocalDate birthday;


  public UserRegisterDTO() {

  }

  public UserRegisterDTO(String firstName, String lastName, String email, String password, String address, String ort, String plz, LocalDate birthday) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
  }

  public UserRegisterDTO(UUID id, String firstName, String lastName, String email, String password, String address, String ort, String plz, LocalDate birthday) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserRegisterDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserRegisterDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegisterDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegisterDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserRegisterDTO setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getOrt() {
    return ort;
  }

  public UserRegisterDTO setOrt(String ort) {
    this.ort = ort;
    return this;
  }

  public String getPlz() {
    return plz;
  }

  public UserRegisterDTO setPlz(String plz) {
    this.plz = plz;
    return this;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public UserRegisterDTO setBirthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }
}
