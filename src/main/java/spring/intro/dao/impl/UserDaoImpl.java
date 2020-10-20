package spring.intro.dao.impl;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import spring.intro.dao.AbstractDao;
import spring.intro.dao.UserDao;
import spring.intro.model.User;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User add(User user) {
        return super.add(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return super.getById(id, User.class);
    }

    @Override
    public List<User> getAllUsers() {
        return super.getAll(User.class);
    }
}
