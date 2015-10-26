package com.dohbedoh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Allan on 23/10/2015.
 */
public abstract class AbstractDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() { return sessionFactory.getCurrentSession(); }

    public void persist(Object entity) {
        getSession().persist(entity);
    }

    public void delete(Object entity) {
        getSession().delete(entity);
    }
}