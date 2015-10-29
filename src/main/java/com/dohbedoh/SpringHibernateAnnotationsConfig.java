package com.dohbedoh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Allan on 24/10/2015.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
public class SpringHibernateAnnotationsConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolder() {
        PropertySourcesPlaceholderConfigurer propertyPlaceholder = new PropertySourcesPlaceholderConfigurer();
        return propertyPlaceholder;
    }
}
