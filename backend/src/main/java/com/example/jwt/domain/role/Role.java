package com.example.jwt.domain.role;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.authority.Authority;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role extends ExtendedEntity {

  @Column(name = "name", nullable = false, unique = true)
  @Enumerated(EnumType.STRING)
  private RoleEnum name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_authority",
      joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
  )
  private Set<Authority> authorities = new HashSet<>();

  public Role() {
  }

  public Role(UUID id, RoleEnum name, Set<Authority> authorities) {
    super(id);
    this.name = name;
    this.authorities = authorities;
  }

  public RoleEnum getName() {
    return name;
  }

  public Role setName(RoleEnum name) {
    this.name = name;
    return this;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public Role setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
    return this;
  }
}
