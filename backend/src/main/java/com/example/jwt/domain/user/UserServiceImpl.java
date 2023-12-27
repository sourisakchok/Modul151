package com.example.jwt.domain.user;

import com.example.jwt.core.audit.UserAware;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.level.LevelEnum;
import com.example.jwt.domain.level.LevelRepository;
import com.example.jwt.domain.role.RoleEnum;
import com.example.jwt.domain.role.RoleRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RoleRepository roleRepository;
  private final LevelRepository levelRepository;

  private final UserAware userAware;

  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
                         BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, LevelRepository levelRepository, UserAware userAware) {
    super(repository, logger);
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleRepository = roleRepository;
    this.levelRepository = levelRepository;
    this.userAware = userAware;
  }

  @Override
  public User register(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.getRoles().add(roleRepository.findByName(RoleEnum.CLIENT));
    user.setLevel(levelRepository.findByName(LevelEnum.BRONZE));
    return save(user);

  }

//  @Override
//  public Optional<User> retrievePrincipal() {
//    return userAware.getCurrentAuditor();
//  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByEmail(email).map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  public Optional<User> retrievePrincipal() throws UsernameNotFoundException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();

      if (principal instanceof UserDetailsImpl) {
        return Optional.of(((UserDetailsImpl) principal).user());
      }
    }

    return Optional.empty();
  }
}
