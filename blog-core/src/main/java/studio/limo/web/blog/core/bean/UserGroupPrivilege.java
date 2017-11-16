package studio.limo.web.blog.core.bean;

import javax.persistence.*;

@Entity
@Table(name = "T_USER_GROUP_PRIVILEGE")
public class UserGroupPrivilege extends BaseBean{

    @Column(name = "ACCESS_ID", length = 20)
    private String accessID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_GROUP_OID", nullable = false)
    private UserGroup userGroup;

    public UserGroupPrivilege(){}

    public String getAccessID() {
        return accessID;
    }

    public void setAccessID(String accessID) {
        this.accessID = accessID;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
