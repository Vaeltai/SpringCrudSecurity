package web.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "roles")
@Entity
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {}

    public Role(String name) {
        this.name = name;
        if (Objects.equals("USER", name)){
            id = 1L;
        } else if (Objects.equals("ADMIN", name)){
            id = 2L;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role: " + name + " ";
    }

    @Override
    public String getAuthority() {
        return name;
    }
}