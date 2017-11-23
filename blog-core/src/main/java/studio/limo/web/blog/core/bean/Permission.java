package studio.limo.web.blog.core.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "T_PERMISSION")
public class Permission extends BaseBean{
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "NAME",length = 20,nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "T_ROLE_PERMISSION",
            joinColumns = {@JoinColumn(name = "PERMISSION_OID", referencedColumnName = "oid")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_OID", referencedColumnName = "oid")})
    private Set<Role> roles;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "PERMISSION_OID")
    private Set<Menu> menus;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
