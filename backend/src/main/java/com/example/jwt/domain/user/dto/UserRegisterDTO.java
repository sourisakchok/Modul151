package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.plz.Plz;

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

  @NotBlank(message = "{validation.plz.notblank}")
  @Pattern(regexp = "\\d+", message = "{validation.plz.pattern}")
  private String plz;

  @NotNull(message = "{validation.birthday.notnull}")
  @Past(message = "{validation.birthday.past}")
  private LocalDate birthday;


  public UserRegisterDTO() {

  }

  public UserRegisterDTO(String firstName, String lastName, String email, String password, String address, String plz, LocalDate birthday) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.plz = plz;
    this.birthday = birthday;
  }

  public UserRegisterDTO(UUID id, String firstName, String lastName, String email, String password, String address, String plz, LocalDate birthday) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.plz = plz;
    this.birthday = birthday;
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
}
