package studio.limo.web.blog.core.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import studio.limo.web.blog.core.bean.AdminUser;


public interface AdminUserDao extends PagingAndSortingRepository<AdminUser, Long>, QueryByExampleExecutor<AdminUser> {

    AdminUser findByAccount(String account);
}
