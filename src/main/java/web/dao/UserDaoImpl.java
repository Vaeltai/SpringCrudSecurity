package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;
import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDaoImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        em.persist(user);
    }

    @Override
    public void update(Long id, User user) {
        User userForUpdate = show(id);
        userForUpdate.setUsername(user.getName());
        userForUpdate.setRoles(user.getRoles());
        if(!Objects.equals(show(id).getPassword(), bCryptPasswordEncoder.encode(user.getPassword()))){
            userForUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
    }

    @Override
    public List<User> getListUsers() {
        String hql = "FROM User";
        Query query = em.createQuery(hql, User.class);
        return query.getResultList();
    }

    @Override
    public void remove(long id) {
        em.remove(show(id));
    }

    @Override
    public User show(long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findUserByUsername(String username){
        String hql = "FROM User where name=:username";
        Query query = em.createQuery(hql, User.class)
                .setParameter("username", username);
        return (User) query.getResultList().get(0);
    }
}
