package studio.limo.web.blog.core.dao;

import org.springframework.data.repository.CrudRepository;
import studio.limo.web.blog.core.bean.UserMenu;

public interface UserMenuDao extends CrudRepository<UserMenu, Long> {
}
