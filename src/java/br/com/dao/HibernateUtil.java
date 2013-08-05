/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author rafael
 */
public abstract class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    static{
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        sessionFactory = configuration.buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory(){
        
        if(sessionFactory == null){
            
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        
        return sessionFactory;
    }
    
    public static Session openSession(){
        
        return getSessionFactory().openSession();
    }
}
