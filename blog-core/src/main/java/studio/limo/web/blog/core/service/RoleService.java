package studio.limo.web.blog.core.service;

import studio.limo.web.blog.core.bean.Role;

public interface RoleService extends GenericEntityService<Role, Long>{
    Role findRoleByName(String name);
}
