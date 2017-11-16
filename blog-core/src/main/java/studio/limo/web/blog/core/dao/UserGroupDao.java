package studio.limo.web.blog.core.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import studio.limo.web.blog.core.bean.UserGroup;

public interface UserGroupDao extends PagingAndSortingRepository<UserGroup, Long>, QueryByExampleExecutor<UserGroup> {

    UserGroup findByGroupId(String groupId);
}
