package studio.limo.web.blog.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studio.limo.web.blog.core.bean.Menu;
import studio.limo.web.blog.core.bean.Permission;
import studio.limo.web.blog.core.bean.Role;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute("loginForm")
    public LoginForm initLoginForm(){
        return new LoginForm();
    }
//
//    @RequestMapping(value = "article-list.html")
//    @RequiresRoles("admin")
//    public String test(){
//        return "article-list";
//    }

    @RequestMapping(value = "welcome",method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(Model model){
        Subject currentUser = SecurityUtils.getSubject();
        User user =(User) currentUser.getSession().getAttribute("userInfo");
        Set<Menu> menus = new HashSet<>();

        for (Permission permission : getPermission(user)){
            menus.addAll(permission.getMenus());
        }

        model.addAttribute("menus",menus);
        System.out.println("User:" + user.getOid());
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, Model model){
        String account = loginForm.getAccount();
        String password = loginForm.getPassword();
        logger.debug("User:" + account + " try login");
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getSession().setAttribute("account",account);
        currentUser.getSession().setAttribute("userInfo",userService.findByAccount(account));
        try{
            currentUser.login(token);
        }catch (Exception e){
            logger.debug("User:" + account + " login failed!");
            return "login";
        }
        logger.debug("User:" + account + " login success!");
        return "redirect:/index";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login";
    }

    @RequestMapping(value = "404", method = RequestMethod.GET)
    public String notFound(){
        return "404";
    }

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String error(){
        return "404";
    }

    private Set<Permission> getPermission(User user){
        Set<Permission> permissions = new HashSet<>();
        for (Role role : user.getRoles()){
            permissions.addAll(role.getPermissions());
        }
        return permissions;
    }

}
