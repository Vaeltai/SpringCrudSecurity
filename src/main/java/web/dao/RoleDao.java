package web.dao;

import web.model.Role;
import web.model.User;
import java.util.List;

public interface RoleDao {
    List<Role> getRoles(User user);
}
