package com.dohbedoh;

import com.dohbedoh.dao.CompanyDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Allan on 23/10/2015.
 */
public class SpringHibernateAnnotations {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringHibernateAnnotationsConfig.class);
        context.refresh();

        CompanyDAO companyDAO = context.getBean(CompanyDAO.class);
        System.out.println(companyDAO.findAll().size());
    }
}
