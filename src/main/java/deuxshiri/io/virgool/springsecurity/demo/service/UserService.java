package deuxshiri.io.virgool.springsecurity.demo.service;

import deuxshiri.io.virgool.springsecurity.demo.entity.User;
import deuxshiri.io.virgool.springsecurity.demo.user.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User findByUsername(String username);

  void save(UserDetails userDetails);
}
