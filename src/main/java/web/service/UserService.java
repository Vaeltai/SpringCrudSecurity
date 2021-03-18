package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);
    List<User> getListUsers();
    User show(long id);
    void update(long id, User user);
    void remove(long id);
}