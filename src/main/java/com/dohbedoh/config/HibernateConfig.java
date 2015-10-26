package com.dohbedoh.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Allan on 24/10/2015.
 */
@Configuration
@PropertySource("classpath:database/hibernate.properties")
public class HibernateConfig {

    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Bean
    public LocalSessionFactoryBean setupSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.dohbedoh.model");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("dialect", dialect);
        hibernateProperties.setProperty("show_sql", showSql);
        hibernateProperties.setProperty("format_sql", formatSql);

        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            SessionFactory sessionFactory,
            DataSource dataSource) {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
