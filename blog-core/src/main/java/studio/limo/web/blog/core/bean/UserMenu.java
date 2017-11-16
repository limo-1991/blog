package studio.limo.web.blog.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER_MENU")
public class UserMenu extends BaseBean{
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", length = 255, nullable = false)
    private String Name;

    @Column(name = "ACCESS_ID", length = 20)
    private String accessId;

    @Column(name = "HREF", length = 255)
    private String href;

    public UserMenu(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
