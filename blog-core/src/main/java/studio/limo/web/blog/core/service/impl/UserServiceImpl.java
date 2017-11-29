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
import studio.limo.web.blog.core.bean.Menu;
import studio.limo.web.blog.core.bean.Permission;
import studio.limo.web.blog.core.bean.Role;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.dao.MenuDao;
import studio.limo.web.blog.core.dao.RoleDao;
import studio.limo.web.blog.core.dao.UserDao;
import studio.limo.web.blog.core.exception.ResourceAlreadyExistException;
import studio.limo.web.blog.core.exception.ResourceNotFoundException;
import studio.limo.web.blog.core.service.UserService;
import studio.limo.web.blog.core.util.DateUtil;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;

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

    @Autowired
    private MenuDao menuDao;

    @PostConstruct
    public void init(){
        if (!userDao.findAll().iterator().hasNext()){
            Set<Permission> permissions = new HashSet<>();
            Permission p1 = new Permission();
            p1.setName("admin");
            p1.setCreateDate(DateUtil.getCurrentDate());
            permissions.add(p1);

            Set<Role> roles = new HashSet<>();
            Role role = new Role();
            role.setName("admin");
            role.setCreateDate(DateUtil.getCurrentDate());
            role.setPermissions(permissions);
            roles.add(role);

            User user = new User();
            user.setAccount("admin");
            user.setPassword("admin");
            user.setUserName("admin");
            user.setRoles(roles);
            user.setCreateDate(DateUtil.getCurrentDate());
            userDao.save(user);

            Menu menu = new Menu();
            menu.setCreateDate(DateUtil.getCurrentDate());
            menu.setName("管理员管理");
            menu.setHref("menu-admin");
            menu.setPermission(p1);

            Menu menu1 = new Menu();
            menu1.setCreateDate(DateUtil.getCurrentDate());
            menu1.setName("角色管理");
            menu1.setHref("/admin-role");
            menu1.setSuperMenu(menu);
            Menu menu2 = new Menu();
            menu2.setCreateDate(DateUtil.getCurrentDate());
            menu2.setName("权限管理");
            menu2.setHref("/admin-permission");
            menu2.setSuperMenu(menu);
            Menu menu3 = new Menu();
            menu3.setCreateDate(DateUtil.getCurrentDate());
            menu3.setName("管理员列表");
            menu3.setHref("/admin-list");
            menu3.setSuperMenu(menu);


            Menu menu4 = new Menu();
            menu4.setCreateDate(DateUtil.getCurrentDate());
            menu4.setName("系统管理");
            menu4.setHref("menu-system");
            menu4.setPermission(p1);

            Menu menu5 = new Menu();
            menu5.setCreateDate(DateUtil.getCurrentDate());
            menu5.setName("栏目管理");
            menu5.setHref("/system-category");
            menu5.setSuperMenu(menu4);

            menuDao.save(menu);
            menuDao.save(menu1);
            menuDao.save(menu2);
            menuDao.save(menu3);
            menuDao.save(menu4);
            menuDao.save(menu5);


        }
    }

    @Override
    public User findByAccount(String account) {
        return userDao.findByAccount(account);
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

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for (User user: userDao.findAll()){
            users.add(user);
        }
        return users;
    }

}
