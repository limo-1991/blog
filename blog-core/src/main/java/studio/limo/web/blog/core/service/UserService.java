package studio.limo.web.blog.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.exception.ResourceAlreadyExistException;

import java.util.List;

public interface UserService extends GenericEntityService<User, Long>{

    User findByAccount(String account);

    List<User> findByUserGroupOid(Long groupOid);

    Page<User> findByCriteria(User user, Pageable pageable);

}
