package com.example.jwt.domain.user;

import com.example.jwt.core.audit.UserAware;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.level.LevelEnum;
import com.example.jwt.domain.level.LevelRepository;
import com.example.jwt.domain.plz.Plz;
import com.example.jwt.domain.plz.PlzRepository;
import com.example.jwt.domain.role.RoleEnum;
import com.example.jwt.domain.role.RoleRepository;
import com.example.jwt.domain.user.dto.UserDTO;
import com.example.jwt.domain.user.dto.UserRegisterDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

  private final PlzRepository plzRepository;

  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
                         BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, LevelRepository levelRepository, UserAware userAware, PlzRepository plzRepository) {
    super(repository, logger);
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleRepository = roleRepository;
    this.levelRepository = levelRepository;
    this.userAware = userAware;
    this.plzRepository = plzRepository;
  }

  @Override
  public User register(UserRegisterDTO userRegisterDTO) {
    // Suche die PLZ-Entität basierend auf dem PLZ-String
    Plz plzEntity = plzRepository.findById(userRegisterDTO.getPlz())
            .orElseThrow(() -> new RuntimeException("PLZ nicht gefunden"));

    // Erstelle das User-Objekt und weise Werte zu
    User newUser = new User();
    newUser.setFirstName(userRegisterDTO.getFirstName());
    newUser.setLastName(userRegisterDTO.getLastName());
    newUser.setEmail(userRegisterDTO.getEmail());
    newUser.setAddress(userRegisterDTO.getAddress());
    newUser.setBirthday(userRegisterDTO.getBirthday());
    newUser.setPlz(plzEntity); // Weise die gefundene PLZ-Entität zu

    // Speichere das User-Objekt in der Datenbank
    //return userRepository.save(newUser);

    newUser.setPassword(bCryptPasswordEncoder.encode(userRegisterDTO.getPassword()));
    newUser.setRole(roleRepository.findByName(RoleEnum.CLIENT));
    newUser.setLevel(levelRepository.findByName(LevelEnum.BRONZE));
    return save(newUser);

  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByEmail(email).map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  public Optional<User> retrievePrincipal() throws UsernameNotFoundException {
    return userAware.getCurrentAuditor();
  }
}
