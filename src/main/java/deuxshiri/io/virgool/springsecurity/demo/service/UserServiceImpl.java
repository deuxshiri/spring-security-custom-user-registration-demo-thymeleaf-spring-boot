package deuxshiri.io.virgool.springsecurity.demo.service;

import deuxshiri.io.virgool.springsecurity.demo.dao.RoleDao;
import deuxshiri.io.virgool.springsecurity.demo.dao.UserDao;
import deuxshiri.io.virgool.springsecurity.demo.entity.Role;
import deuxshiri.io.virgool.springsecurity.demo.entity.User;
import deuxshiri.io.virgool.springsecurity.demo.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userRepository;

  @Autowired
  private RoleDao roleRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public User findByUsername(String username) {
    // check the DB if the user already exists
    return userRepository.findByUsername(username);
  }

  @Override
  @Transactional
  public void save(UserDetails userDetails) {
    var user = new User();
    // assign user details to user object
    user.setUserName(userDetails.getUsername());
    user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
    user.setFirstName(userDetails.getFirstName());
    user.setLastName(userDetails.getLastName());
    user.setEmail(userDetails.getEmail());

    // give user default role of 'employee'
    user.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_EMPLOYEE")));
    List<Role> role_employee = Arrays.asList(roleRepository.findRoleByName("ROLE_EMPLOYEE"));

    // save the user
    userRepository.save(user);
  }

  @Override
  @Transactional
  public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User((user.getUserName()), user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  }
}
