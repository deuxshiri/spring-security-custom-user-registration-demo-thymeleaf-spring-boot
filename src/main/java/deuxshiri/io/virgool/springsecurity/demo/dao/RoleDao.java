package deuxshiri.io.virgool.springsecurity.demo.dao;

import deuxshiri.io.virgool.springsecurity.demo.entity.Role;

public interface RoleDao {

  Role findRoleByName(String theRoleName);
}
