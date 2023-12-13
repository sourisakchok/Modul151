package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.Email;

public class UserRegisterDTO extends ExtendedDTO {

  private String firstName;

  private String lastName;

  @Email
  private String email;

  private String password;

  private String adresse;
  private String ort;
  private String plz;
  private Date birthday;


  public UserRegisterDTO() {
  }

  public UserRegisterDTO(String firstName, String lastName, String email, String password, String adresse, String ort, String plz, Date birthday) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.adresse = adresse;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
  }

  public UserRegisterDTO(UUID id, String firstName, String lastName, String email, String password, String adresse, String ort, String plz, Date birthday) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.adresse = adresse;
    this.ort = ort;
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

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
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

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
}
