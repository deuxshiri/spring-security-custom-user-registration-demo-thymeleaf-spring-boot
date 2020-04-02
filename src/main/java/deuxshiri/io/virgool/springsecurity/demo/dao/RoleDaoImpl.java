package deuxshiri.io.virgool.springsecurity.demo.dao;

import deuxshiri.io.virgool.springsecurity.demo.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleDaoImpl implements RoleDao {

  private EntityManager entityManager;

  @Autowired
  public RoleDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Role findRoleByName(String theRoleName) {
    // get the current Hibernate session
    Session session = entityManager.unwrap(Session.class);

    // retrieve/read from DB using name
    Query<Role> theQuery = session.createQuery("FROM Role WHERE name=:roleName", Role.class);
    theQuery.setParameter("roleName", theRoleName);

    Role role;
    try {
      role = theQuery.getSingleResult();
    } catch (Exception exc) {
      role = null;
    }
    return role;
  }
}
