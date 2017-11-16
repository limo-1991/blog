import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studio.limo.web.blog.core.BlogCoreApplication;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.dao.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogCoreApplication.class)
@EnableAutoConfiguration
public class DbTest {
    @Autowired
    private UserDao adminUserDao;

    @Test
    public void adminUserDaoTest(){
        User adminUser = new User();
        adminUser.setAccount("admin");
        adminUser.setPassword("admin");
        adminUser.setUserName("limo");
        adminUserDao.save(adminUser);
        User adminUser2 = new User();
        adminUser2.setAccount("admin2");
        adminUser2.setPassword("admin2");
        adminUser2.setUserName("limo2");
        adminUserDao.save(adminUser2);
        System.out.println("admin user save!");
    }

    @Test
    public void deleteAdminUser(){
        Iterable<User> adminUser = adminUserDao.findAll();
        adminUserDao.delete(adminUser);
        System.out.println("admin user delete!");
    }
}
