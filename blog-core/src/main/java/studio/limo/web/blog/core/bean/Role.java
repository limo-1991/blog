package studio.limo.web.blog.core.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "T_ROLE")
public class Role extends BaseBean{
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "ROLE_NAME", unique = true, length = 20, nullable = false)
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "USER_OID")
    private User user;

    public Role(){

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
