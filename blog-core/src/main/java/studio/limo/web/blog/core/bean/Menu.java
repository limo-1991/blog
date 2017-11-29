package studio.limo.web.blog.core.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "T_MENU")
public class Menu extends BaseBean{
    @NotEmpty
    @Size(max = 20)
    @Column(name = "NAME", unique = true, length = 20, nullable = false)
    private String name;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "HREF", nullable = false)
    private String href;

    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERMISSION_OID")
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_OID")
    private Menu superMenu;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_OID")
    private Set<Menu> subMenus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Menu getSuperMenu() {
        return superMenu;
    }

    public void setSuperMenu(Menu superMenu) {
        this.superMenu = superMenu;
    }

    public Set<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(Set<Menu> subMenus) {
        this.subMenus = subMenus;
    }
}
