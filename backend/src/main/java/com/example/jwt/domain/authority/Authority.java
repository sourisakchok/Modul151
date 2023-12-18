package com.example.jwt.domain.authority;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.role.Role;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority extends ExtendedEntity {

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
  private Set<Role> roles;

  public Authority() {
  }

  public Authority(String name, Set<Role> roles) {
    this.name = name;
    this.roles = roles;
  }

  public Authority(UUID id, String name, Set<Role> roles) {
    super(id);
    this.name = name;
    this.roles = roles;
  }

  public String getName() {
    return name;
  }

  public Authority setName(String name) {
    this.name = name;
    return this;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public Authority setRoles(Set<Role> roles) {
    this.roles = roles;
    return this;
  }
}
