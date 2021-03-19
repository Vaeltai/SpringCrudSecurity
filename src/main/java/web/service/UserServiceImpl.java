package web.service;

import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    public User show(long id) {
        return userDao.show(id);
    }

    @Override
    public void update(long id, User user) {
        userDao.update(id, user);
    }

    @Override
    public void remove(long id) {
        userDao.remove(id);
    }
}


