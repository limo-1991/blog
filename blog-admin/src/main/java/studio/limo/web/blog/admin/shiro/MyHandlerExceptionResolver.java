package studio.limo.web.blog.admin.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@ControllerAdvice
public class MyHandlerExceptionResolver{

    @ExceptionHandler(value = AuthorizationException.class)
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String requestType = httpServletRequest.getHeader("X-Requested-With");
        if(e instanceof AuthorizationException){
            httpServletResponse.setStatus(413);//无权限异常  主要用于ajax请求返回
            httpServletResponse.addHeader("Error-Json", "{code:413,msg:'nopermission',script:''}");
            httpServletResponse.setContentType("text/html;charset=utf-8");
            if("XMLHttpRequest".equals(requestType)){
                return new ModelAndView();
            }
            return new ModelAndView("redirect:/404");
        }
        return null;
    }
}
