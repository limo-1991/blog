package studio.limo.web.blog.core.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_USER_GROUP")
public class UserGroup extends BaseBean{
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "GROUP_ID", length = 20, nullable = false, unique = true)
    private String groupId;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "GROUP_NAME", length = 100, nullable = false)
    private String groupName;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "userGroup")
    private Set<UserGroupPrivilege> userGroupPrivileges = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "userGroup")
    private Set<User> users = new HashSet<>();

    public UserGroup(){}

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserGroupPrivilege> getUserGroupPrivileges() {
        return userGroupPrivileges;
    }

    public void setUserGroupPrivileges(Set<UserGroupPrivilege> userGroupPrivileges) {
        this.userGroupPrivileges = userGroupPrivileges;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
