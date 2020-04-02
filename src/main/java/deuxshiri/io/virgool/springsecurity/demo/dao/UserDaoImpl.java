package deuxshiri.io.virgool.springsecurity.demo.dao;

import deuxshiri.io.virgool.springsecurity.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {

  private EntityManager entityManager;

  @Autowired
  public UserDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public User findByUsername(String username) {
    // get the current Hibernate session
    Session session = entityManager.unwrap(Session.class);

    // retrieve/read from DB using username
    Query<User> theQuery = session.createQuery("FROM User WHERE userName=:username", User.class);
    theQuery.setParameter("username", username);

    User user;
    try {
      user = theQuery.getSingleResult();
    } catch (Exception exc) {
      user = null;
    }
    return user;
  }

  @Override
  public void save(User user) {
    Session session = entityManager.unwrap(Session.class);

    // create the User
    session.saveOrUpdate(user);
  }
}
