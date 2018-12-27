import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tun.int2jpg.hibernate.model.User;
import tun.int2jpg.hibernate.service.HibernateService;

import java.util.List;
import java.util.Random;

public class TestHibernate {
    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateService.buildSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Random random = new Random();
            int userId = (int)(Math.random() * 1000 + 1);

            System.out.println("Saving Random 3 Users..");
            for(int i=0; i<3; i++) {
                User user = new User();
                user.setUserName("test_user"+userId);
                user.setPassword("test_password"+userId);
                userId++;
                session.save(user);
            }
            System.out.println("Saved. Selecting..");

            Query query = session.createQuery("from User");
            List<User> userList = query.list();
            if(userList != null){
                for (User user : userList) {
                    System.out.println("userName:"+user.getUserName()+", password:"+user.getPassword());
                }
            }

            // Committing The Transactions To The Database
            transaction.commit();
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                System.out.println("\nTransaction is being rolled back.");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
