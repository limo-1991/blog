package studio.limo.web.blog.admin.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;

@Controller
@RequiresRoles("admin")
public class AdminController {
}
