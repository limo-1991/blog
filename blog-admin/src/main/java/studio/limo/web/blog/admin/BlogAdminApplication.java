package studio.limo.web.blog.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import studio.limo.web.blog.core.BlogCoreConfiguration;

@SpringBootApplication
@Import(BlogCoreConfiguration.class)
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class);
    }


    /**
     * 配置404页面
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return (container ->{
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            container.addErrorPages(error404Page);
        });
    }
}
