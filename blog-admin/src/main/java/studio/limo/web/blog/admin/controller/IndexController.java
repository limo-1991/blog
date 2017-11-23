package studio.limo.web.blog.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.service.UserService;

import javax.validation.Valid;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute("loginForm")
    public LoginForm initLoginForm(){
        return new LoginForm();
    }

//    @RequestMapping(value = "{page}.html")
//    public String index(@PathVariable(name = "page") String page){
//        return page;
//
//    }

    @RequestMapping(value = "article-list.html")
    @RequiresRoles("admin")
    public String test(){
        return "article-list";
    }

    @RequestMapping(value = "index")
    public String index(Model model){
        Subject currentUser = SecurityUtils.getSubject();
        User user =(User) currentUser.getSession().getAttribute("userInfo");

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

}
