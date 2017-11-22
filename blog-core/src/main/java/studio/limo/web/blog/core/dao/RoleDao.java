package studio.limo.web.blog.core.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import studio.limo.web.blog.core.bean.Role;

import java.util.List;

public interface RoleDao extends PagingAndSortingRepository<Role, Long>, QueryByExampleExecutor<Role> {
}
