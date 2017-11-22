package studio.limo.web.blog.core.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "T_USER")
public class User extends BaseBean {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "ACCOUNT", unique = true, length = 20, nullable = false)
    private String account;

    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "passwords.format.is.wrong")
    @Column(name = "PASSWORD", length = 40, nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "USER_NAME", length = 100, nullable = false)
    private String userName;


    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_OID", referencedColumnName = "oid")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_OID", referencedColumnName = "oid")})
    private Set<Role> roles;

    public User(){

    }

    public User(String account, String password, String userName) {
        this.account = account;
        this.password = password;
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
