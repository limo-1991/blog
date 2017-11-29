package studio.limo.web.blog.admin.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import studio.limo.web.blog.core.bean.User;
import studio.limo.web.blog.core.service.UserService;

import java.util.List;

@Controller()
@RequestMapping(value = "admin")
@RequiresRoles("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "admin-list";
    }
}
