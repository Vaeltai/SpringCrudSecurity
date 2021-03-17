package web.dao;

import web.model.Role;
import web.model.User;
import java.util.List;

public interface UserDao {
    List<User> getListUsers();
    void saveUser(User user);
    void update(Long id, User user);
    void remove(long id);

    User show(long id);
    User findUserByUsername(String username);
//    List<Role> getRoles(User user);
}