package studio.limo.web.blog.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties({JpaProperties.class})
@EnableJpaRepositories(
        basePackages = {"studio.limo.web.blog.core"}
)
public class BlogDbConfig {
    private static final Logger logger = LoggerFactory.getLogger(BlogDbConfig.class);
    @Autowired
    private JpaProperties jpaProperties;

    public BlogDbConfig() {
    }

    @Primary
    @Bean(
            name = {"dataSource"}
    )
    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    public DataSource dataSource() {
        logger.debug("create dataSource");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(
            name = {"entityManagerFactory"}
    )
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
        logger.debug("create entityManagerFactory");
        return builder.dataSource(dataSource).packages("studio.limo.web.blog.core").properties(this.jpaProperties.getHibernateProperties(dataSource)).build();
    }

    @Primary
    @Bean(
            name = {"transactionManager"}
    )
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        logger.debug("create transactionManager");
        JpaTransactionManager txManager = new JpaTransactionManager(entityManagerFactory);
        txManager.setNestedTransactionAllowed(true);
        return txManager;
    }
}
