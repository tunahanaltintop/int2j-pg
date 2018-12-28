package tun.int2jpg.hibernate.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class HibernateService {

    private static Session session;
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();

        // Creating Hibernate SessionFactory Instance
        sessionFactory = metaData.getSessionFactoryBuilder().build();
        return sessionFactory;
    }

    private static Session getSession() {
        if (session == null) {
            session = buildSessionFactory().openSession();
        }
        if (!session.getTransaction().isActive()) {
            session.getTransaction().begin();
        }
        return session;
    }

    private static void commit() {
        if (session != null && session.getTransaction().isActive()) {
            session.getTransaction().commit();
        }
    }

    private static void rollback() {
        if (session != null && session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
    }

    public static <T> T getObject(Class<T> objClass, Serializable pKey) {
        return getSession().get(objClass, pKey);
    }

    public static List<?> listObjects(String hql){
        Query query = getSession().createQuery(hql);
        return query.list();
    }

    public static void saveOrUpdateObject(Object object) {
        try {
            getSession().saveOrUpdate(object);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public static void saveObject(Object object) {
        try {
            getSession().save(object);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }
}