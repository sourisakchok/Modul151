package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  @Override
  public User register(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    user.setRole(Role.CLIENT);

    Set<Authority> authorities = new HashSet<>();
    authorities.add(Authority.CAN_PLACE_ORDER);
    authorities.add(Authority.CAN_RETRIEVE_PURCHASE_HISTORY);
    authorities.add(Authority.CAN_RETRIEVE_PRODUCTS);
    user.setAuthorities(authorities);

    return save(user);
  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    super(repository, logger);
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByEmail(email).map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  @Override
  public User register(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return save(user);
  }
}
