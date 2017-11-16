package studio.limo.web.blog.core.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import studio.limo.web.blog.core.bean.User;

import java.util.List;


public interface UserDao extends PagingAndSortingRepository<User, Long>, QueryByExampleExecutor<User> {

    User findByAccount(String account);

    List<User> findByUserGroupOid(Long groupOid);
}
