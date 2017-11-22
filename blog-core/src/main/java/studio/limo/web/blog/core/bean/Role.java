package studio.limo.web.blog.core.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "T_ROLE")
public class Role extends BaseBean{
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "NAME", unique = true, length = 20, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "ROLE_OID", referencedColumnName = "oid")},
            inverseJoinColumns = {@JoinColumn(name = "USER_OID", referencedColumnName = "oid")})
    private Set<User> users;


    public Role(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
