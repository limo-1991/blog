package studio.limo.web.blog.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.limo.web.blog.core.dao.AdminUserDao;


@Controller
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private AdminUserDao userDao;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model){
        model.addAttribute("name",userDao.findByAccount("admin").getUserName());
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam(value = "u") String account, @RequestParam(value = "p") String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject currentUser = SecurityUtils.getSubject();

        currentUser.login(token);

        return account + ",登录成功";
    }

}
