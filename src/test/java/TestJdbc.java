import tun.int2jpg.jdbc.Int2jpgJdbc;

public class TestJdbc {
    public static void main(String[] args) {
        System.out.println("\nSelecting...");
        Int2jpgJdbc.runSelectQuery("SELECT * FROM sp_user");

        System.out.println("\nSelected. Inserting..");
        Int2jpgJdbc.runInsertUpdateDeleteQuery("INSERT INTO sp_user VALUES ('test_user2','test_password2')");

        System.out.println("Inserted. Selecting..");
        Int2jpgJdbc.runSelectQuery("SELECT * FROM sp_user");

        System.out.println("\nSelected. Updating..");
        Int2jpgJdbc.runInsertUpdateDeleteQuery("UPDATE sp_user SET user_name='test_user3', password='test_password3' where user_name='test_user2'");

        System.out.println("Updated. Selecting..");
        Int2jpgJdbc.runSelectQuery("SELECT * FROM sp_user");

        System.out.println("\nSelected. Deleting..");
        Int2jpgJdbc.runInsertUpdateDeleteQuery("DELETE FROM sp_user where user_name='test_user3'");

        System.out.println("Deleted. Selecting..");
        Int2jpgJdbc.runSelectQuery("SELECT * FROM sp_user");
    }
}
