package spring.intro.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import spring.intro.dao.AbstractDao;
import spring.intro.dao.UserDao;
import spring.intro.model.User;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public User add(User user) {
        return super.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return super.getAll(User.class);
    }
}
