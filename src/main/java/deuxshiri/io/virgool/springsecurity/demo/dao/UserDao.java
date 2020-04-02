package deuxshiri.io.virgool.springsecurity.demo.dao;

import deuxshiri.io.virgool.springsecurity.demo.entity.User;

public interface UserDao {

  User findByUsername(String username);

  void save(User user);
}
