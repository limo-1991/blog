package studio.limo.web.blog.core.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import studio.limo.web.blog.core.bean.UserGroupPrivilege;

import java.util.List;

public interface UserGroupPrivilegeDao extends PagingAndSortingRepository<UserGroupPrivilege,Long>, QueryByExampleExecutor<UserGroupPrivilege>{
    List<UserGroupPrivilege> findByUserGroupOid(Long oid);
}
