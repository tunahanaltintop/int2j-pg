package tun.int2jpg.hibernate.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateService {

    // read configuration and build session factory
    final StandardServiceRegistry registry =new StandardServiceRegistryBuilder()
                    .configure("hibernate_cfg.xml")
                    .build();

    SessionFactory sessionFactory = null;

    public HibernateService(){
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }


}
