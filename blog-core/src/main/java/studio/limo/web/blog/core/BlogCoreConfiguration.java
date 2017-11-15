package studio.limo.web.blog.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("studio.limo.web.blog.core")
public class BlogCoreConfiguration {

    public BlogCoreConfiguration(){

    }

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        jsonConverter.getObjectMapper().registerModule(new Hibernate5Module());
//        return jsonConverter;
//    }
}
