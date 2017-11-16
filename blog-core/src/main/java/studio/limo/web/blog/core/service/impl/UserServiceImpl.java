package studio.limo.web.blog.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.dao.UserDao;
import studio.limo.web.blog.core.exception.ResourceAlreadyExistException;
import studio.limo.web.blog.core.exception.ResourceNotFoundException;
import studio.limo.web.blog.core.service.UserService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Service("userService")
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.READ_COMMITTED,
        readOnly = true,
        rollbackFor = {Exception.class}
)
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    public UserServiceImpl(){

    }

    @PostConstruct
    public void init(){
        if (!userDao.findAll().iterator().hasNext()){
            User user = new User();
            user.setAccount("admin");
            user.setPassword("admin");
            user.setUserName("admin");
            userDao.save(user);
        }
    }

    @Override
    public User findByAccount(String account) {
        return userDao.findByAccount(account);
    }

    @Override
    public List<User> findByUserGroupOid(Long groupOid) {
        return userDao.findByUserGroupOid(groupOid);
    }

    @Override
    public Page<User> findByCriteria(User user, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("account", ExampleMatcher.GenericPropertyMatchers.contains());

        return userDao.findAll(Example.of(user, matcher), pageable);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            readOnly = false,
            rollbackFor = {Exception.class}
    )
    @Override
    public <S extends User>  void save(S s) throws ResourceAlreadyExistException {

        User user = userDao.findByAccount(s.getAccount());
        if (user != null){
            throw new ResourceAlreadyExistException("User with account(" + s.getAccount() + ") already exist!");
        }else {
            userDao.save(s);
        }
    }


    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            readOnly = false,
            rollbackFor = {Exception.class}
    )
    @Override
    public <S extends User> void update(S s) throws ResourceNotFoundException {
        if (!userDao.exists(s.getOid())){
            throw new ResourceNotFoundException("Can not find User with oid(" + s.getOid() + ")!");
        }else {
            userDao.save(s);
        }
    }

    @Override
    public User findById(Long id) {
        return userDao.findOne(id);
    }


    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            readOnly = false,
            rollbackFor = {Exception.class}
    )
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

}
