import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studio.limo.web.blog.core.BlogCoreApplication;
import studio.limo.web.blog.core.bean.Menu;
import studio.limo.web.blog.core.bean.Permission;
import studio.limo.web.blog.core.bean.Role;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.dao.UserDao;
import studio.limo.web.blog.core.util.DateUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogCoreApplication.class)
@EnableAutoConfiguration
public class DbTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void adminUserDaoTest(){
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
        System.out.println("admin user save!");
    }

    @Test
    public void deleteAdminUser(){
        Iterable<User> adminUser = userDao.findAll();
        userDao.delete(adminUser);
        System.out.println("admin user delete!");
    }

    @Test
    public void test(){

        User user = userDao.findOne(1L);
        System.out.println("USER---->" + user.getUserName());
        Iterator<Role> iterator = user.getRoles().iterator();
        while (iterator.hasNext()){
            Role role = iterator.next();
            System.out.println("ROLE---->" + role.getName() );
            /*while (role.getPermissions().iterator().hasNext()){
                Permission permission = role.getPermissions().iterator().next();
                System.out.println("PERMISSION---->" + permission.getName());
               *//* while (permission.getMenus().iterator().hasNext()){
                    Menu menu = permission.getMenus().iterator().next();
                    System.out.println("MENU---->" + menu.getName());
                }*//*
            }*/
        }
        System.out.println("test");
    }
}
