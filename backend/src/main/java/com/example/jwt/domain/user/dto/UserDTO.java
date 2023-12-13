package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.role.dto.RoleDTO;

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

  private String adresse;
  private String ort;
  private String plz;
  private Date birthday;

  @Valid
  private Set<RoleDTO> roles;

  public UserDTO() {
  }

  public UserDTO(String firstName, String lastName, String email, String adresse, String ort, String plz, Date birthday, Set<RoleDTO> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.adresse = adresse;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
    this.roles = roles;
  }

  public UserDTO(UUID id, String firstName, String lastName, String email, String adresse, String ort, String plz, Date birthday, Set<RoleDTO> roles) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.adresse = adresse;
    this.ort = ort;
    this.plz = plz;
    this.birthday = birthday;
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

  public Set<RoleDTO> getRoles() {
    return roles;
  }

  public void setRoles(Set<RoleDTO> roles) {
    this.roles = roles;
  }
}
