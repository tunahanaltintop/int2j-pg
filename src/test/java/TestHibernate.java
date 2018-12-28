import tun.int2jpg.hibernate.model.User;
import tun.int2jpg.hibernate.service.HibernateService;

import java.util.List;

public class TestHibernate {
    public static void main(String[] args) {
        try {
            System.out.println("Selecting..");
            List<User> userList = (List<User>) HibernateService.listObjects("from User");
            if (userList != null) {
                for (User user : userList) {
                    System.out.println("userName:" + user.getUserName() + ", password:" + user.getPassword());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
