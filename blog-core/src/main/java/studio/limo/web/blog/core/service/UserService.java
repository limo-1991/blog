package studio.limo.web.blog.core.service;

import studio.limo.web.blog.core.bean.Role;
import studio.limo.web.blog.core.bean.User;

import java.util.List;

public interface UserService extends GenericEntityService<User, Long>{
    User findByAccount(String account);

    List<Role> findRolesByUser(User user);
}
