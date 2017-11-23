package studio.limo.web.blog.core.service;

import studio.limo.web.blog.core.bean.Permission;
import studio.limo.web.blog.core.bean.Role;

import java.util.List;

public interface RoleService extends GenericEntityService<Role, Long>{
    Role findRoleByName(String name);

    List<Permission> findPermissionByRole(Role role);
}
