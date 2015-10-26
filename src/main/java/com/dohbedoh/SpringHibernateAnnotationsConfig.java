package com.dohbedoh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Allan on 24/10/2015.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
public class SpringHibernateAnnotationsConfig {

    public static final Logger logger = LoggerFactory.getLogger(SpringHibernateAnnotationsConfig.class);

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolder() {
        PropertySourcesPlaceholderConfigurer propertyPlaceholder = new PropertySourcesPlaceholderConfigurer();
//        propertyPlaceholder.setLocations(
//                new ClassPathResource("classpath:database/database.properties"),
//                new ClassPathResource("classpath:database/hibernate.properties")
//        );
        logger.info("Configurer created!");
        return propertyPlaceholder;
    }
}
